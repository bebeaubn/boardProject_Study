package org.bebeaubn.configs;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bebeaubn.model.member.LoginFailureHandler;
import org.bebeaubn.model.member.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties(FileUploadConfig.class)
public class SecurityConfig {

    @Autowired
    private FileUploadConfig fileUploadConfig;

    @Bean
    /* 인증설정 */
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.formLogin(f -> {
            f.loginPage("/member/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .successHandler(new LoginSuccessHandler())
                    .failureHandler(new LoginFailureHandler());
        }); // DSL , 보안적인 부분이 발생하면 여기서 패치가 가능하다

        http.logout(c -> {
            c.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                    .logoutSuccessUrl("/member/login");
            // 주소 입력해주면 로그아웃하고 로그아웃 하고 넘어갈 페이지 설명, Handler도 있음
        });
            //인증설정 - 로그인
        http.headers(c -> {
            c.frameOptions(o -> o.sameOrigin());
        });

        //인가설정 - 접근통제 시작
        http.authorizeHttpRequests(c -> {
            c.requestMatchers("/mypage/**").authenticated() // 회원 전용(로그인한 회원만 접근 가능)
                    .requestMatchers("/admin/**").hasAuthority("ADMIN") // 관리자 권한만 접근
                    .requestMatchers(   "/front/css/**",
                            "/front/js/**",
                            "/front/images/**",

                            "/mobile/css/**",
                            "/mobile/js/**",
                            "/mobile/images/**",

                            "/admin/css/**",
                            "/admin/js/**",
                            "/admin/images/**",

                            "/common/css/**",
                            "/common/js/**",
                            "/common/images/**",
                            fileUploadConfig.getUrl() + "**"
                            ).permitAll()
                    .anyRequest().permitAll(); // 나머지 페이지는 권한 필요 X
            //인가설정 - 접근통제 끝
        });


        http.exceptionHandling((c-> {
            c.authenticationEntryPoint((req, resp, e) -> {
                String URI =req.getRequestURI();
                if(URI.indexOf("/admin") != -1) { //관리자 페이지 -401 응답코드 //401페이지 나오게 만듬
                    resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Not AUTHORIZED");

                }else{ // 회원전용 페이지 (예 = /mypage) -> 로그인 페이지 이동
                    String url =req.getContextPath() + "/member/login";
                    resp.sendRedirect(url);

                    //인증실패시 구현 로그인 페이지 이동
            }
        });
        }));



        //인가설정 - 접근통제

                return http.build();
        }


    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}