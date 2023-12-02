package com.example.mypage;

import com.example.noticeboard.NoticeBoardModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MypageController {
    @Autowired
    private MypageService mypageService;
    @Autowired
    private MypageRepository mypageRepository; 

    @GetMapping("/account")
    public String account(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String UID = (String) session.getAttribute("UID"); // 세션에서 UID를 가져옵니다.

        // 세션 없을 경우
        if (UID == null) { 
            return "redirect:/login";
        }

        List<NoticeBoardModel> userPosts = mypageService.getUserPosts(UID);
        model.addAttribute("posts", userPosts); // 사용자의 게시글을 모델에 추가합니다.
        model.addAttribute("UID", UID); // UID를 모델에 추가합니다.
        
        return "/account"; 
    }
    
    @GetMapping("/accountnotice/{seq}")
    public String accountNotice(@PathVariable("seq") Integer seq, Model model) {
        NoticeBoardModel post = mypageRepository.findById(seq).orElse(null); // seq로 게시글 검색

        // 게시글이 존재하지 않을 경우
        if (post == null) {
            return "redirect:/account";
        }

        model.addAttribute("post", post); // 게시글 정보를 모델에 추가
        return "/accountnotice";
    }

}
