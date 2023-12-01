package com.example.noticeboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NoticeBoardController {
	private final NoticeBoardService noticeBoardService;
	@Autowired
	public NoticeBoardController(NoticeBoardService noticeBoardService) {
		this.noticeBoardService = noticeBoardService;
	}
	
	@PostMapping("/notice")
	public String postNotice(@RequestParam(name = "title") String title, 
            @RequestParam(name = "content") String content) {
		System.out.println("postNotice호출됨");
		noticeBoardService.insertNotice("작성자", title, content);
		return "redirect:/noticeboard";
	}
	
	@GetMapping("/noticeboard")
    public String getAllNotices(Model model) { // model : controller와 view사이의 데이터 교환을 담당하는 객체
		List<NoticeBoardModel> notices = noticeBoardService.getAllNotices();
        model.addAttribute("notices", notices);
        return "/noticeboard";
    }
}
