package com.board.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	private int pageNum;
	private int listQty;
	
	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int pageNum , int listQty) {
		this.pageNum = pageNum;
		this.listQty = listQty;
	}
	// 파라미터 완성해서 돌려주는 메서드
	
	public String getParameterLink() {
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("listQty", this.listQty);
		
		System.out.println("uri String : "+ builder.toUriString());
		return builder.toUriString();
	}
	
}