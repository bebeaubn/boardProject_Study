package org.bebeaubn.configs;

import org.bebeaubn.model.member.MemberInfo;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {

        String email = null;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       // Object principal = auth.getPrincipal();   //비회원 -String(문자열) :anonymousUSER, 회원 -UserDetails 객체구현
        if(auth != null && auth.getPrincipal() instanceof MemberInfo){
           MemberInfo member = (MemberInfo)auth.getPrincipal();
           email = member.getEmail();

        }
        // 세션에 담아서 편하게 쓰기위해 담음 --> MemberUtil 거기에 사용


        return Optional.ofNullable(email);
    }
}