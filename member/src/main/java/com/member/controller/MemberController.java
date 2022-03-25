package com.member.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.member.domain.MemberVO;
import com.member.service.MemberService;

@Controller
@RequestMapping("/member/")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	
	//회원가입 폼
	@GetMapping("signup")
	public void signup() {
		System.out.println("signup 요청!");
		
	}
	
	//회원가입 처리
	@PostMapping("signup")
	public String signupPro(@ModelAttribute("member") MemberVO member, Model model) {
		System.out.println("signup 처리 요청");
		
		//넘어온 회원 정보 DB 저장 -> 서비스야 회원가입  처리해봐 
		int result = memberService.addMember(member);
		model.addAttribute("result", result);
		
		
		return "member/signupPro";
	}
	
	@GetMapping("main")
	public void main(HttpServletRequest request) {
		System.out.println("main 요청");
		//쿠키꺼내기
		Cookie[] coos = request.getCookies();
		if(coos != null) {
			for(Cookie c : coos) {
				if(c.getName().equals("cookieID")) {
					if(request.getAttribute("memID")==null) {
						//비로그인 -> session, cookie
						memberService.createSession(c.getValue());
					}
					//로기인 -> cookie
					
				}
			}
		}
		
		//로그인일떼
		//쿠키 있따 : 쿠키갱신하여 로그인
		
		
		//쿠기없다
		//비로그인일떄
		
		//쿠키 꺼내기
//		Cookie[] coos = request.getCookies();
//		if(coos != null) {
//			for(Cookie c : coos) {
//				if(c.getName().equals("cookieId")) System.out.println("cookie ID value : " +c.getValue());
//				if(c.getName().equals("cookiePw")) System.out.println("cookie PW value : " +c.getValue());
//			}
//		}
		
		
	}
	
	//로그인 폼
	@GetMapping("login")
	public String login(HttpServletRequest request) {
		System.out.println("로그인 폼 요청!");
		
		Cookie[] coos = request.getCookies();
		if(coos != null) {
			for(Cookie c : coos) {
				//no sessoin, yes cookie
				if(c.getName().equals("cookieID")) {
					//꺼내서 로그인 처리 -> session. cookie (갱신=쿠키생성)
					memberService.createSession(c.getValue());
					return "member/main"; //메인페이지 보여줘
				}
			}
		}
		
		return "member/login"; //원래 요청한 페이지 보여줘
		
		
	}
	//로그인 처리
	
//	@PostMapping("login")
//	// 세션 꺼내기방법 #1 컨트롤로에서 매개변수로 받아 서비스에 전달해주기
//	public String loginPro(MemberVO member, Model model, HttpSession session) {
//		System.out.println("로그인 처리 요청!"); 
//		
//		//서비스야 로그인 처리 좀 해~~
//		int result = memberService.idPwCheck(member, session);
//		//result 결과를 확인하고 1= 로그인 = session에 속성추가
//		// 0 = login 실패
//		model.addAttribute("result",result);
//		return "member/loginPro";
//	}
	
	@PostMapping("login")
	// 세션 꺼내기방법 #2 여기서 안끝나고 
	public String loginPro(MemberVO member,String auto, Model model, HttpServletResponse response) {
		System.out.println("로그인 처리 요청!"); 
		
		//서비스야 로그인 처리 좀 해~~
		int result = memberService.idPwCheck(member, auto,response);
//		int result = memberService.idPwCheck(member);
		//result 결과를 확인하고 1= 로그인 = session에 속성추가
		// 0 = login 실패
		model.addAttribute("result",result);
		return "member/loginPro";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
//		session.invalidate(); //session 에 있는 모든 속성 삭제
		session.removeAttribute("memId"); // memId 속성만 삭제
		
		return "redirect:/member/main";
	}
	
	@GetMapping("mypage")
	public void mypage() {
		System.out.println("mypage 요청!");
	}
	
	//회원정보 수정 폼 요청: a태그 록은 버튼받아오면 GET방식으로 구성한다.
	@GetMapping("modify")
	public void modify(HttpSession session, Model model) {
		System.out.println("modyfy 폼요청");
		//로그인한 회원 정보를 전체 뽑아서 뷰에 전달
		//서비스야 회원정보 DB에서 가져와서 나한테 전달해줘
		//#1 컨트롤러에서 id뽑아 서비스에 전달
		//세션에서 id뽑아서 Object로 돌려주니 String으로 형변환해서 담기 
		String id = (String) session.getAttribute("memId");
//		MemberVO member= memberService.getMember(id);
		
		//#2 방법2
		MemberVO member= memberService.getMember();
		//뷰에전달
		model.addAttribute("member", member);
		//내가 받아서 뷰에 전달할게
	}
	
	//회원정보 수정 처리 요청
	@PostMapping("modify")
	public String modifyPro(MemberVO member , Model model) { // pw, age, email
		System.out.println(" modify처리요청");
		// 서비스야~ DB에서 넘어온 데이터로 수정처리 해줘~
		int result = memberService.modifyMember(member);
		System.out.println("result modify : "+ result);
		//update springMember set pw=#{pw}, age=#{age}, email={email} where id =#{id}
		model.addAttribute("result", result);
		
		return "member/modifyPro";
	}
	
	   // 회원 탈퇴 폼 페이지 요청 
   @GetMapping("delete")
   public void delete() {
      System.out.println("delete 폼 요청!");
   }
   
   // 회원 탈퇴 처리 요청 
   @PostMapping("delete")
   public String deletePro(MemberVO member, Model model) { // pw 만 넘어옴 
      System.out.println("delete 처리 요청!!");
      
      // 서비스야, id와 pw가 맞는지 체크해서 맞으면 회원 정보 삭제해줘~~ 
      int result = memberService.deleteMember(member);
      System.out.println("C delete result : " + result);
      model.addAttribute("result", result);
      
      return "member/deletePro";
   }
	
	
}