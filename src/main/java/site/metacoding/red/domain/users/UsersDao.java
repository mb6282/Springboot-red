package site.metacoding.red.domain.users;

import java.util.List;

public interface UsersDao {
	public Integer insert(Users users);
	public Users findById(Integer id);
	public List<Users> findAll();
}
