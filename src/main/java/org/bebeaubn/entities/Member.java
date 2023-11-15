package org.bebeaubn.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.bebeaubn.commons.constants.MemberType;


import java.time.LocalDateTime;

@Data
@Entity
public class Member {

    @Id
    private Long userNo;

    private String email;

    private String password;

    private String userNm;

    private String mobile;

    private MemberType mtype = MemberType.USER;

    private LocalDateTime regDt;
    private LocalDateTime modDt;


}
