package kr.co.sboard.controller.entity;

import jakarta.persistence.*;
import kr.co.sboard.dto.ArticleDTO;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Article")
@DynamicInsert
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int no;
    private  int parent;
    private  int comment;
    private  String cate;
    private  String title;
    private  String content;
    private  int file;
    private  int hit;
    private  String writer;
    private  String regip;
    @CreationTimestamp
    private LocalDateTime rdate;
    @ColumnDefault("Y")
    private  String useyn;

    public ArticleDTO toDTO() {

        return ArticleDTO.builder()
                .no(no)
                .parent(parent)
                .comment(comment)
                .cate(cate)
                .title(title)
                .content(content)
                .hit(hit)
                .writer(writer)
                .regip(regip)
                .rdate(rdate)
                .useyn(useyn)
                .build();

    }


}
