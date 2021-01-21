package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value = "/guestbook")
public class GuestbookController {
	
	//필드
	@Autowired
	GuestbookDao gbDao;
	
	//입력창 및 리스트 출력
	@RequestMapping(value = "/addList", method = {RequestMethod.GET, RequestMethod.POST} )
	public String addList(Model model) {
		System.out.println("addList");
		
		model.addAttribute("guestbookList", gbDao.getList());
		
		return "addList";
	}
	
	//방명록 등록
	@RequestMapping(value="/add", method = {RequestMethod.GET, RequestMethod.POST} )
	public String add(@ModelAttribute GuestbookVo gbVo) {
		System.out.println("add");
		
		gbDao.insert(gbVo);
		
		return "redirect:/guestbook/addList";
	}
	
	//방명록 삭제 폼
	@RequestMapping(value="/deleteForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm() {
		System.out.println("deleteForm");
		
		return "deleteForm";
	}
	
	//방명록 삭제
	@RequestMapping(value="/delete") //방식 생략(GET/POST 둘다)
	public String delete(@ModelAttribute GuestbookVo gbVo, Model model) {
		System.out.println("delete");
		
		int result = gbDao.delete(gbVo);
		
		//삭제 실패일 경우 어트리뷰트영역에 result저장하고 deleteForm으로 포워드
		//파라미터로 리다이렉트하려 했으나, 글 고유번호가 주소창에 보이는 문제가 있음
		if(result == 0) {
			model.addAttribute("delResult", result);
			return "deleteForm";
		}else {
			return "redirect:/guestbook/addList";
		}
	}
}
