package site.metacoding.red.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.web.dto.reqest.boards.UpdateDto;
import site.metacoding.red.web.dto.reqest.boards.WriteDto;
import site.metacoding.red.web.dto.response.RespDto;

@RequiredArgsConstructor
@RestController
public class BoardsController {

	private final BoardsDao boardsDao;
	//private : 모든 상태는 직접 접근을 허용하면 안 됨
	
	@PostMapping("/boards")
	//다른 controller의 메서드 이름과 동일성을 부여해주는게 좋음
	//그래서 write대신 usersController에 있는 insert를 사용했음
	public RespDto<?> insert(WriteDto writeDto){ // x-www머시기(key=value)
		boardsDao.insert(writeDto);
		return new RespDto<>(1, "글쓰기 성공", null);
	}
	
	@GetMapping("/boards")
	public RespDto<?> select(){
		return new RespDto<>(1,"글조회성공",boardsDao.findAll());
	}
	
	@PutMapping("/boards/{id}")
	public RespDto<?> update(@PathVariable Integer id, UpdateDto updateDto){
		//1.영속화
		Boards boards = boardsDao.findById(id);
		//2.변경
		boards.글전체수정(updateDto);
		//3.전체 업데이트
		return new RespDto<>(1, "글수정성공", null);
	}
	
	@DeleteMapping("/boards/{id}")
	public RespDto<?> delete(@PathVariable Integer id){
		boardsDao.delete(id);
		return new RespDto<>(1, "글삭제성공", null);
		//return new RespDto<>(1, "글삭제성공", boardsDao.delete(id)); => 오류남
		//delete는 return이 없는 void타입 메서드라 body에 들어가지 못한다.
	}
}
