package kr.co.sboard.controller.entity;

import jakarta.persistence.*;
import kr.co.sboard.dto.TermsDTO;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="Terms")
public class TermsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="no")
    private int no;
    private String terms;
    private String privacy;

    public TermsDTO toDTO() {

        return TermsDTO.builder()
                .no(no)
                .terms(terms)
                .privacy(privacy)
                .build();

    }

}
