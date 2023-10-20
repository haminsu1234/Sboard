package kr.co.sboard.service;

import kr.co.sboard.controller.entity.UserEntity;
import kr.co.sboard.dto.UserDTO;
import kr.co.sboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void inputUser(UserDTO dto){
        dto.setPass(passwordEncoder.encode(dto.getPass()));
        UserEntity entity = dto.toEntity();

        repo.save(entity);
    }


    public int countUid(String uid){
       return repo.countByUid(uid);
    }

    public int countNick(String nick) {
        return repo.countByNick(nick);
    }

    public int countHp(String hp){
        return repo.countByHp(hp);
    }
}
