package site.metacoding.red.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.web.dto.reqest.users.JoinDto;
import site.metacoding.red.web.dto.reqest.users.UpdateDto;
import site.metacoding.red.web.dto.response.RespDto;

@RequiredArgsConstructor
@RestController
public class UsersController {

	private final UsersDao usersDao;
	
	@GetMapping("/users/{id}")
	public RespDto<?> getUsers(@PathVariable Integer id) {
		return new RespDto<>(1, "성공", usersDao.findById(id));
				//new 할 때 제네릭에 <> 값을 생략해도 됨
	}
	
	@GetMapping("/users")
	public RespDto<?> getUserList(){
		return new RespDto<>(1, "성공", usersDao.findAll());
	}
	
	@PostMapping("/users")
	public RespDto<?> insert(JoinDto joinDto) {
		usersDao.insert(joinDto);
		return new RespDto<>(1, "회원가입완료", null);
	}
	
//	@PutMapping("users/{id}")
//	public RespDto<?> update(@PathVariable Integer id, UpdateDto updateDto){
//		Users users = new Users();
//		users.setId(id);
//		users.setUsername(updateDto.getUsername());
//		users.setPassword(updateDto.getPassword());
//		users.setEmail(updateDto.getEmail());
//		usersDao.update(users);
//		return new RespDto<>(1,"회원수정완료",null);
//	}
	
	@PutMapping("users/{id}")
	public RespDto<?> update(@PathVariable Integer id, UpdateDto updateDto){
		//1번 : id로 select 하자. (영속화 퍼시스턴스)
		//DB에 있는 하나의 row를 java object로 옮기는 것을 영속화라고 함
		Users usersPS = usersDao.findById(id);
		
		//2번 : 변경 (User Entity에 업데이트 책임 수만큼 메서드를 만들어두고 적용)
		//=의미있는 메서드(수행해야 할 업데이트만을 만든다)를 만듬 
		usersPS.전체수정(updateDto);
		
		//3번 : 영속화된 오브젝트로 update하기 (전체 업데이트)
		//3번은 영속화되어 모든 값이 있으므로 전체 업데이트가 가능하다.
		usersDao.update(usersPS);
		
		//통신으로 Users를 받지 않고 메서드에도 set으로 값을 받지 않으므로
		//이 메서드의 경우 Users에 setter가 없어도 됨
		
		return new RespDto<>(1,"회원수정완료",null);
	}
	
	@PutMapping("users/{id}/password")
	public RespDto<?> updatePassword(@PathVariable Integer id, String password){
		//1번 : 영속화
		Users usersPS = usersDao.findById(id);
		
		//2번 : 변경(Users에서 메서드 만들기)
		usersPS.패스워드수정(password);
		//사실은 이런 1번, 2번 코드가 Service에 있어야 함
		
		//3번 : 영속화된 오브젝트로 update하기 (전체 업데이트)
		usersDao.update(usersPS);

		return new RespDto<>(1,"회원패스워드수정완료",null);
	}
	
	@DeleteMapping("/users/{id}")
	public RespDto<?> delete(@PathVariable Integer id){
		usersDao.delete(id);
		return new RespDto<>(1,"회원삭제완료",null);
	}
}
