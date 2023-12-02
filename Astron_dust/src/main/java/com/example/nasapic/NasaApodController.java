package com.example.nasapic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
public class NasaApodController {

    private final NasaApodService nasaApodService;

    public NasaApodController(NasaApodService nasaApodService) {
        this.nasaApodService = nasaApodService;
    }

    @GetMapping("/nasapictures")
    public String getApodsForDateRange(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String UID = (String) session.getAttribute("UID"); // 세션에서 UID를 가져옵니다.

        // 세션 없을 경우
        if (UID == null) { 
            return "redirect:/login";
        }

        model.addAttribute("UID", UID); // UID를 모델에 추가합니다. 이렇게 하면 뷰에서 UID를 사용할 수 있습니다.

        // 오늘의 NasaApod 데이터를 가져옵니다. 없다면 NASA API를 사용하여 가져옵니다.
        NasaApod apodToday = nasaApodService.getTodayApod();

        // 데이터베이스에서 모든 NasaApod 데이터를 가져옵니다.
        List<NasaApod> apods = nasaApodService.getAllApods();

        model.addAttribute("apods", apods); // 모든 NasaApod 데이터를 모델에 추가합니다.

        return "/nasapictures";
    }
}

