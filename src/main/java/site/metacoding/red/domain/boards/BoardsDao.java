package site.metacoding.red.domain.boards;

import java.util.List;

import site.metacoding.red.domain.boards.mapper.BoardsDetail;
import site.metacoding.red.web.dto.reqest.boards.WriteDto;

public interface BoardsDao {
	public void insert(WriteDto writeDto);
	public Boards findById(Integer id);
	public List<Boards> findAll();
    //나중에 findAll할 때도 join해서 받아야 할 것임
    //왜냐면 userId가 아닌 작성자 정보를 줘야 하기 때문에
	public void update(Boards boards);
	public void delete(Integer id);
	public BoardsDetail findByIdtoDetail(Integer id);
}
