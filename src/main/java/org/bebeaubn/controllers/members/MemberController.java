package org.bebeaubn.controllers.members;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bebeaubn.commons.MemberUtil;
import org.bebeaubn.commons.Utils;
import org.bebeaubn.entities.Member;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final Utils utils;
    private final MemberUtil memberUtils;

    @GetMapping("/join")
    public String join() {

        return utils.tpl("member/join");
    }

    @GetMapping("/login")
    public String login(String redirectURL, Model model) {

        model.addAttribute("redirectURL", redirectURL);

        return utils.tpl("member/login");
    }

    @ResponseBody
    @GetMapping("/info")
    public void info() {
        Member member = memberUtils.getMember();

        if(memberUtils.isLogin()){
            log.info(member.toString());
        }

        log.info("로그인 여부 : {}", memberUtils.isLogin());
    }
    /*
    public void info(@AuthenticationPrincipal MemberInfo memberInfo) {
        log.info(memberInfo.toString());
    */
    /*
    public void info(Principal principal) {
        String email = principal.getName();
        log.info(email);
    }
     */
}



