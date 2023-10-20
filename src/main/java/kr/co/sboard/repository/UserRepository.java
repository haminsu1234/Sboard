package kr.co.sboard.repository;

import kr.co.sboard.controller.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity , String> {

    public int countByUid(String uid);
    public int countByNick(String nick);
    public int countByHp(String hp);


}
