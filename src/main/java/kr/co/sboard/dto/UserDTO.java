package kr.co.sboard.dto;

import kr.co.sboard.controller.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {


    private  String uid;
    private  String pass;
    private  String name;
    private  String nick;
    private  String email;
    private  String hp;
    private  String role;
    private  String zip;
    private  String addr1;
    private  String addr2;
    private  String regip;
    private LocalDateTime regDate;
    private  LocalDateTime leaveDate;

    public UserEntity toEntity() {

        return UserEntity.builder()
                .uid(uid)
                .name(name)
                .pass(pass)
                .hp(hp)
                .nick(nick)
                .email(email)
                .zip(zip)
                .addr1(addr1)
                .addr2(addr2)
                .regip(regip)
                .regDate(regDate)
                .leaveDate(leaveDate)
                .build();

    }


}
