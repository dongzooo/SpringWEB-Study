package com.member.mapper;

import javax.servlet.http.HttpSession;

import com.member.domain.MemberVO;

//회원 가입 mapper 
public interface MemberMapper {
	
	//회원추가
	public int addMember(MemberVO member);
	
	//로그인처리 (id,pw 확인)
	public int idPwCheck(MemberVO member, HttpSession session);
	
	//로그인 처리 ㄴ방법2
	public int idPwCheck(MemberVO member);
	
	//회원정보 한명 받아오기
	public MemberVO getMember(String id);
	
	//회원 정보 수정하기
	public int updateMember(MemberVO member);
	
	//탈퇴시 회원정보 삭제
	public int deleteMember(String id);
	
	//아이디 중복확인 처리
	public int idCount(String id);
	
}
