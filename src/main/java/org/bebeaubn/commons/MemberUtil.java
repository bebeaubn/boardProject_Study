package org.bebeaubn.commons;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.bebeaubn.commons.constants.MemberType;
import org.bebeaubn.entities.Member;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberUtil {
    private final HttpSession session;

    /**
     * 로그인 여부 체크
     * @return
     */
    public boolean isLogin() {
        return getMember() != null;
    }


    /**
     * 관리자 여부 체크
     * @return
     */
    public boolean isAdmin() {
        return isLogin() && getMember().getMtype() == MemberType.ADMIN;
    }

    //게시글 수정시 가능하게 하는 관리자 권한 더 상세하게 해줘야함

    public Member getMember() {
        return (Member)session.getAttribute("loginMember");

        //의존성만 주입해서 바로 간편하게 쓰기 위해 주입
    }
}


// 직접 세션에서 데이터 가져올수 있게 만들었다