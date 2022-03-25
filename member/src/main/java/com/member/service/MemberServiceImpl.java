package com.member.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import com.member.domain.MemberVO;
import com.member.mapper.MemberMapper;

//서비스 구현 클래스 : 기능 구현
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	
	//회원추가 가입처리 구현
	@Override
	public int addMember(MemberVO member) {
		
		//DB 접근해서추가
		int result = memberMapper.addMember(member);
		
		return result;
	}

	//로그인 처리
	@Override
	public int idPwCheck(MemberVO member,  HttpSession session) {
		int result = memberMapper.idPwCheck(member, session);
		//로그인 성공
		if (result ==1) {
			//session에 memId라는 이름으로 사용자 id 추가 --> 로그인 확인용으로 사용할 것임
			session.setAttribute("memId", member.getId());
		}
		// 0 = 로그인 실패
		
		return result;
		
	}

	@Override
	public int idPwCheck(MemberVO member) {
		int result =memberMapper.idPwCheck(member);
		
//		ServletRequestAttributes sra= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		
		if (result ==1) {
			//session에 memId라는 이름으로 사용자 id 추가 --> 로그인 확인용으로 사용할 것임
			session.setAttribute("memId", member.getId());
		}
		return result;
	}
	
	  public int idPwCheck(MemberVO member, String auto, HttpServletResponse response) { // 세션 방법#2.
	      int result = memberMapper.idPwCheck(member);
	      System.out.println("result service :" + result);

	      // result 결과를 확인하고 1 = 로그인 = session에 속성추가 
	      if(result == 1) {
	         // session에 memId라는 이름으로 사용자 id 추가 --> 로그인확인용으로 사용할것임 
	         getSession().setAttribute("memId", member.getId()); 
	         if(auto != null) { // 자동로그인 체크했다 -> 쿠키 만들기 
	            Cookie c1 = new Cookie("cookieId", member.getId()); 
	            Cookie c2 = new Cookie("cookiePw", member.getPw()); 
	            c1.setMaxAge(60); // 24시간 
	            c2.setMaxAge(60); // 24시간 
	            c1.setPath("/");
	            c2.setPath("/");
	            response.addCookie(c1);
	            response.addCookie(c2);
	         }
	         
	      }
	      // 0 = 로그인실패 
	      
	      return result;
	   }


	//회원정보 가져오기
	@Override
	public MemberVO getMember(String id) {
		
		MemberVO member =  memberMapper.getMember(id);
		
		return member; //컨트롤러한테 전달해 줄게
	}

	//회원정보 컨트롤러에서 받아오기
	@Override
	public MemberVO getMember() {
		
		String id = (String)getSession().getAttribute("memId");
		
		MemberVO member =  memberMapper.getMember(id);
		
		return member;
	}

	
	//회원정보 수정 처리 구현
	@Override
	public int modifyMember(MemberVO member) {
		
		String id = (String)getSession().getAttribute("memId");
		member.setId(id); //member에 id값도 채워서 mapper에 보내기
		
		int result =memberMapper.updateMember(member);
		
		
		return result;
	}

	// 회원 탈퇴 처리 구현 
	   @Override
	   public int deleteMember(MemberVO member) {

	     
	      String id = (String)getSession().getAttribute("memId"); 
	      member.setId(id);  // member에 id값도 체워서 mapper에 보내기 
	      
	      // id, pw 체크 
	      int result = memberMapper.idPwCheck(member); 
	      
	      if(result == 1) {  // 맞으면 delete
	         // DB 삭제 처리 
	         int deleteRes = memberMapper.deleteMember(member.getId()); 
	         System.out.println("S delete result : " + deleteRes); // 개발자만 확인 
	         // 로그아웃 
	         getSession().invalidate();
	      }
	      // 안맞으면 아무것도 안함 
	      
	      return result; // Controller에게 id,pw 맞는지의 결과만 전달 
	   }
	   
	   private HttpSession getSession() {
		   HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
		   HttpSession session = request.getSession(); 
		   return session;
	   }
	   

  @Override
   public int login(MemberVO member, String auto, HttpServletResponse response) {
	  
	  if(member.getId()==null) {
		  member = new MemberVO();
	  }
	  HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
	  Cookie[] coos = request.getCookies();
			  
	  
	  int result =memberMapper.idPwCheck(member);
			  
	   return 0;
   }
	@Override
	public void createSession(String id) {
		HttpSession session = getSession();
		if(session.getAttribute("memID")!= null) {
			getSession().setAttribute("memId", id);
		}
	}

	@Override
	public void createCookie() {
		//쿠키를 꺼내서 ID와 pw값 꺼내 쿠키 생성 (갱신)
	}





	
	
	

}