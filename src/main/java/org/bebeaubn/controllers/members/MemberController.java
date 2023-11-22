package org.bebeaubn.controllers.members;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bebeaubn.commons.MemberUtil;
import org.bebeaubn.commons.Utils;
import org.bebeaubn.entities.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController implements commonProcess {

    private final Utils utils;
    private final MemberUtil memberUtil;

    @GetMapping("/join")
    public String join() {
        @ModelAttribute RequestJoin form, Model;
        medel
    }

}


    @GetMapping("/login")
    public String login(String redirectURL, Model model) {
        commonProcess(model,Utils.getMessage("로그인","common"));

        saveService.join(form, errors);

        if(errors.hasErrors()) {
            return utils.tpl("member/join");
        }

        return "redirect:/member/login";
    }

    @ResponseBody
    @GetMapping("/info")
    public void info() {

        Member member = memberUtil.getMember();
        if (memberUtil.isLogin()) {
            log.info(member.toString());
        }

        log.info("로그인 여부 : {}", memberUtil.isLogin());
    }
    /*
    public void info() {
        MemberInfo member = (MemberInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        log.info(member.toString());
    }
    */
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

