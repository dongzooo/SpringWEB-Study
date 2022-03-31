package com.board.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria { //페이지처리시 필요한 데이터들
	private int pageNum; // 페이지 번호
	private int listQty; // 한페이지에 보여줄 게시물 개수
	
	private String type; // 검색조건 T title, W writer, C content, TW TC TWC 붙여서 담기
	private String keyword; //검색키워드
	
	
	
	public Criteria() {
		this(1,10);
	}
	
	//type 배열로 변경해주는 메서드 (배열로 떼서 처리)
	public String[] getTypeArr() {
		//type 문자열이 null이면 빈 String 배열 생성해서 리턴,
		// type문자열에 값이 있으면 알파벳 분리해서 배열로 담아 리턴
		return type == null ? new String[] {} : type.split(""); 
	}
	
	public Criteria(int pageNum , int listQty) {
		this.pageNum = pageNum;
		this.listQty = listQty;
	}
	// 파라미터 완성해서 돌려주는 메서드
	
	public String getParameterLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("listQty", this.listQty)
				.queryParam("type", this.type)
				.queryParam("keyword", this.keyword);
			
		System.out.println("uri String : "+ builder.toUriString());
		return builder.toUriString();
	}
}
