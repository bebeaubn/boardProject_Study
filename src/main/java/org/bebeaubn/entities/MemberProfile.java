package org.bebeaubn.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

public class MemberProfile {
    @Entity
    @Data
    public class MemberProfile {
        @Id
        @GeneratedValue
        private Long seq;

        @Column(length=100)
        private String image;

        @ToString.Exclude
        @OneToOne(mappedBy = "profile")
        private Member member;
    }
}
