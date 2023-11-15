package org.bebeaubn.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BoardData {

    @Id
    @GeneratedValue
    private Long seq;


    @Column(length = 100, nullable = false)
    private String subject;


    @Lob
    @Column(nullable = false)
    private String content;



    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime regDt;



    @Column(insertable = false)
    @UpdateTimestamp
    private LocalDateTime modDt;




}
