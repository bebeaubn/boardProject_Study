package org.bebeaubn.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Entity
@Data
public class Configs {
    @Id
    @Column(name="code_", length=45)
    private String code;

    @Lob
    private String value;
}
