package kr.co.sboard.security;

import kr.co.sboard.controller.entity.UserEntity;
import kr.co.sboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserService implements UserDetailsService {
	
	@Autowired
	private UserRepository repo; //인증관리 필터에서 바로 db로 access 할거임 그래서 주입받는것
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//패스워드에 대한 검사는 이전 컴포넌트(AuthenticationProvider)에서 처리되어 사용자 아이디만 넘어옴
		UserEntity user =repo.findById(username)
				.orElseThrow(()->new UsernameNotFoundException(username+" NotFountd"));
		
		//사용자 인증객체 (세션에저장)
		UserDetails userDetails=MyUserDetails.builder()
					.user(user)
					.build();
		
		return userDetails;
	}

}
