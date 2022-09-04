package site.metacoding.red.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;

@RequiredArgsConstructor
@RestController
public class UsersController {

	private final UsersDao usersDao;
	
	@GetMapping("/users/{id}")
	public Users getUsers(@PathVariable Integer id) {
		return usersDao.findById(id);
	}
	
	@GetMapping("/users")
	public List<Users> getUserList(){
		return usersDao.findAll();
	}
	
	@PostMapping("/users")
	public ResponseEntity<?> insert(Users users) {
		usersDao.insert(users);
		return new ResponseEntity<>(HttpStatus.CREATED); // 201번
	}
}
