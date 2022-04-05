package com.board.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/upload/")
public class UploadController {
	
	@GetMapping("uploadForm")
	public void uploadForm() {
		System.out.println("업로드 폼 요청");
	}
	
	@PostMapping("uploadPro")
	public String uploadPro(String msg, MultipartHttpServletRequest request , Model model) {
		
		// 넘어온 파일 정보를 꺼내기 
		// 서버에 파일 저장
		System.out.println("msg : "+msg);
		MultipartFile mf = request.getFile("img");
		System.out.println("mf size : " + mf.getSize());
		System.out.println("mf orgName : "+ mf.getOriginalFilename());
		System.out.println("mf ContentType : "+ mf.getContentType());
		
		String ct = mf.getContentType();
		String type = ct.substring(0, ct.indexOf("/"));
//		if(type.equals("image")) {
//			System.out.println("이미지 파일 아닙니다.");
//			return "redirect:/upload/uploadForm";
//		}
		
		// 서버에 파일 저장
		// #2 파일 저장할 폴더 경로 찾아 save
		String savePath = request.getRealPath("resources\\save");
		System.out.println(savePath);
		// #3 파일이름 (원본파일 이름을 저장할 것인가 no, 파일이름 중복처리해서 서버에 저장)
		// #3-1 오리지날 파일명 + 현재시간 millis + 확장자명
//		String orgName = mf.getOriginalFilename();
//		String ext  = orgName.substring(orgName.lastIndexOf("."));
//		String imgName = orgName.substring(0, orgName.lastIndexOf("."));
//		System.out.println("ext : " + ext);
//		System.out.println("imgname : "+imgName);
//		String newName = imgName + System.currentTimeMillis() + ext;
//		System.out.println("new Name = " + newName);
		
		//#3-2 UUID + orgName
		String orgName = mf.getOriginalFilename();
		UUID uuid = UUID.randomUUID();
		System.out.println("uuid : " + uuid );
		String newName = uuid+orgName;
		
		// #4 파일을 실제로 저장 처리 : 내가만든 파일명과 폴더 전체 경로를 연결해서 그쪽으로 복사해주는 개념
		String imgPath = savePath + "\\" + newName;
		File f = new File(imgPath);
		try { 
			mf.transferTo(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// (#5. ) DB에 파일 이름 저장 : newName
		
		
		// view에 파일명 전달해서 pro페이지에 img로 띄우기 (사용자가 브라우저에서 내가업로드한 서버에 저장)
		model.addAttribute("imgName", newName);
		return "upload/uploadPro";
	}
	
	//다운로드 버튼 보여줘
	@GetMapping("downForm")
	public void downForm() {
		System.out.println("다운로드 버튼 페이지 요청");
	}
	//다운버튼 눌렀을 떄 요청 처리
	@GetMapping("download")
	public void down(HttpServletRequest request) {
		String path =request.getRealPath("resources\\imgs");
		System.out.println(path);
		String filePath = path + "\\tra.PNG";
		File f  = new File(filePath);
		// fileDown : view 이름에 해당하는 값 -> servlet=context.xml에 downloadView클래스 빈으로 등록한 id와 일치
		// downlaodFile :  MODEL로 보내는 실제 데이터 (viewname)
		// f: 다운시킬 실제 파일 객체
		ModelAndView mv = new ModelAndView("fileDown", "downloadFile", f);
	}
	
}
