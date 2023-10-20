package kr.co.sboard.dto;

import kr.co.sboard.controller.entity.TermsEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TermsDTO {

    private int no;
    private String terms;
    private String privacy;

    public TermsEntity toEntity() {

        return TermsEntity.builder()
                .no(no)
                .terms(terms)
                .privacy(privacy)
                .build();

    }

}
