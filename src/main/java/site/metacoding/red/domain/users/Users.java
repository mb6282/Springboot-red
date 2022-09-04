package site.metacoding.red.domain.users;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.red.web.dto.reqest.users.UpdateDto;

@Setter
@Getter
public class Users {
	private Integer id;
	private String username;
	private String password;
	private String email;
	private Timestamp createdAt;
	
	public void 전체수정(UpdateDto updateDto) {
		//영속화가 되어 있으므로 수정할 body값만 받으면 됨
		this.username=updateDto.getUsername();
		this.password=updateDto.getPassword();
		this.email=updateDto.getEmail();
	}
	
	public void 패스워드수정(String password) {
		this.password=password;
	}

}
