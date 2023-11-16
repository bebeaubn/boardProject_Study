package org.bebeaubn.controllers.members;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bebeaubn.commons.MemberUtil;
import org.bebeaubn.commons.Utils;
import org.bebeaubn.entities.BoardData;
import org.bebeaubn.entities.Member;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Transactional
public class MemberController {

    private final Utils utils;
    private final MemberUtil memberUtils;

    private final EntityManager em;

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
        BoardData data = BoardData.builder()
                .subject("제목")
                .content("내용")
                .build();


        em.persist(data);
        em.flush();

        data.setSubject("(수정)제목");
        em.flush();

    }
    }



