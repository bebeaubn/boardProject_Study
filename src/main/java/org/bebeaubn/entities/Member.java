package org.bebeaubn.entities;

import jakarta.persistence.*;
import lombok.*;
import org.bebeaubn.commons.constants.MemberType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = {
        @Index(name = "idx_member_userNm", columnList = "userNm"),
        @Index(name = "idx_member_mobile", columnList = "mobile")
})
public class Member {

    @Id @GeneratedValue(strategy =GenerationType.AUTO)
    private Long userNo;

    @Column(length = 65, unique = true, nullable = false)
    private String email;

    @Column(length = 65, name = "pw", nullable = false)
    private String password;

    @Column(length = 40, nullable = false)
    private String userNm;

    @Column(length = 11)
    private String mobile;



    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberType mtype = MemberType.USER;


    @CreationTimestamp
    private LocalDateTime regDt;


    @UpdateTimestamp
    private LocalDateTime modDt;

    @Transient
    private String tmpData;



    /*
    @Temporal()
    private Data data;
*/
}
