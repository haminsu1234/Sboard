package kr.co.sboard.repository;

import kr.co.sboard.controller.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Integer> {

    //@Query("SELECT a FROM ArticleEntity a WHERE a.useyn = : and cate ='notice' and parent =0 order by `no` DESC limit 1 ,10")
    //public List<ArticleEntity> selectArticleByUseynAndCateAndParent();

    int countByAno(int ano);


}
