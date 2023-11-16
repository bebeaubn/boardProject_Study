package org.bebeaubn.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BoardData extends BaseMember {

    @Id
    @GeneratedValue
    private Long seq;


    @Column(length = 100, nullable = false)
    private String subject;


    @Lob
    @Column(nullable = false)
    private String content;






}
