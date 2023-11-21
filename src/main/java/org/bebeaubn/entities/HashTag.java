package org.bebeaubn.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class HashTag {
    @Id
        @Column(length=30)
        private String tag;

        //@ToString.Exclude
        //@ManyToMany(mappedBy = "tags", fetch= FetchType.EAGER)
        //private List<BoardData> items = new ArrayList<>();
    }

