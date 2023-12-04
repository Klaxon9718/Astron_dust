package com.example.noticeboard;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class NoticeBoardController {
	private final NoticeBoardService noticeBoardService;
	@Autowired
	public NoticeBoardController(NoticeBoardService noticeBoardService) {
		this.noticeBoardService = noticeBoardService;
	}
	
	@PostMapping("/notice")
	public String postNotice(HttpServletRequest request, @RequestParam(name = "title") String title, 
            @RequestParam(name = "content") String content) {
		HttpSession session = request.getSession();
	    String UID = (String) session.getAttribute("UID");
	    
		noticeBoardService.insertNotice(UID, title, content);
		return "redirect:/noticeboard";
	}
	
	@GetMapping("/noticeboard")
    public String getAllNotices(Model model, @RequestParam(name = "page", defaultValue = "0") int page) { // model : controller와 view사이의 데이터 교환을 담당하는 객체
		Pageable pageable = PageRequest.of(page, 8);
		Page<NoticeBoardModel> noticePage = noticeBoardService.getNotices(pageable);
		model.addAttribute("notices", noticePage.getContent());
        model.addAttribute("maxPage", noticePage.getTotalPages());
        return "/noticeboard";
    }
	
	@GetMapping("/onecontent")
	public String getChoiceNotices(Model model, @RequestParam(name = "noticeSeq") int noticeSeq) {
		NoticeBoardModel noticeBoardModel = noticeBoardService.getNoticeBySeq(noticeSeq);
		model.addAttribute("title", noticeBoardModel.getTitle());
		model.addAttribute("content", noticeBoardModel.getContent());
		//날짜 형식 변환
		Date date = noticeBoardModel.getDate();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd.HH:mm");
		String formattedDate = formatter.format(date);
		model.addAttribute("date", formattedDate);
		return "/onecontent";
	}
}
