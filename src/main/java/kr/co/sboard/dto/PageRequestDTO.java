package kr.co.sboard.dto;

import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO {

    @Builder.Default
    private int pg=1;

    @Builder.Default
    private int size=10;

    @Builder.Default
    private String cate="notice";

    @Builder.Default
    private String useyn="Y";

    public Pageable getPageable(String sort){

        return PageRequest.of(this.pg-1, this.size, Sort.by(sort).descending());
    }

}
