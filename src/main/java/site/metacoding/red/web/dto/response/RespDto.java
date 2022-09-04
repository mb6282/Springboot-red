package site.metacoding.red.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//@Data<-getter setter toString이 있는데 헷갈려지니 겟터셋터를 쓰자
@AllArgsConstructor
@Setter
@Getter
public class RespDto<T> {
	private Integer code; //1정상, -1실패
	private String msg; // 통신에 대한 결과 메시지 담기
	private T body; // body가 메서드마다 다르므로 제네릭 사용 
}
