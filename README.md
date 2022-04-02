# SpringWEB-Study
Spring study for web backend development








### 1. MembeController  구조
		
		URL			Method		설명 			jsp페이지 
		-----------------------------------------------------------------------------------
		/member/main		GET		메인페이지 		main.jsp
		/member/signup		GET		회원가입 폼페이지 	signup.jsp
		/member/signup		POST		회원가입 처리 		(signupPro.jsp) 
		/member/login		GET		로그인 폼페이지 	login.jsp
		/member/login		POST		로그인 처리 		(loginPro.jsp) 
		/member/mypage		GET		내정보 페이지 		mypage.jsp
		/member/modify		GET		회원정보수정 폼페이지	modify.jsp
		/member/modify		POST		회원정보수정 처리	(modifyPro.jsp) 
		/member/delete		GET		회원 탈퇴 폼 페이지 	delete.jsp
		/member/delete		POST		회원 탈퇴 처리 		(deletePro.jsp)
		-----------------------------------------------------------------------------------


### 2. BoardController 구조
		
	    URL		Method	설명				JSP 페이지 
	    ------------------------------------------------------------------------
	    /board/list	GET	게시판 목록 			list.jsp
	    /board/write	GET	글작성 폼 			write.jsp
    	/board/write	POST	글작성 처리 
    	/board/content	GET	글내용(본문) 			content.jsp
    	/board/modify	GET	글 수정 폼 			modify.jsp 
    	/board/modify	POST	글 수정 처리 
    	/board/delete	GET	글 삭제 			delete.jsp
    	/board/delete	POST	글 삭제 처리 
    	------------------------------------------------------------------------



### 3. ReplyController 구조 
		
		작업		URL (ajax)			Method 
		-----------------------------------------------------------
		등록		/replies/new			POST
		조회		/replies/{rno}			GET
		삭제		/replies/{rno}			DELETE 
		수정		/replies/{rno}			PUT 
		*전체글(페이지)	/replies/pages/{bno}/{page}	GET
		-----------------------------------------------------------
