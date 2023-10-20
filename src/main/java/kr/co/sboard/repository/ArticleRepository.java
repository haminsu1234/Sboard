package kr.co.sboard.repository;

import kr.co.sboard.controller.entity.ArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {

    //@Query("SELECT a FROM ArticleEntity a WHERE a.useyn = : and cate ='notice' and parent =0 order by `no` DESC limit 1 ,10")
    //public List<ArticleEntity> selectArticleByUseynAndCateAndParent();

    public Page<ArticleEntity> findByUseynAndParentAndCate(String useyn, int parent ,String cate ,Pageable pageable);


}
