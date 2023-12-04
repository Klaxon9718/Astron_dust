package com.example.nasapic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Controller
public class NasaApodController {

    private final NasaApodService nasaApodService;

    public NasaApodController(NasaApodService nasaApodService) {
        this.nasaApodService = nasaApodService;
    }

    @GetMapping("/nasapictures")
    public String getApodsForDateRange(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String UID = (String) session.getAttribute("UID");

        if (UID == null) { 
            return "redirect:/login";
        }

        model.addAttribute("UID", UID);

        Pageable pageable = PageRequest.of(0, 2);

        NasaApod apodToday = nasaApodService.getTodayApod();

        Page<NasaApod> apods = nasaApodService.getAllApods(pageable);

        model.addAttribute("apods", apods);

        return "/nasapictures";
    }
    
    @GetMapping("/nasapictures/{page}")
    public String getApodsForDateRangeWithPage(@PathVariable("page") int page, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String UID = (String) session.getAttribute("UID");

        if (UID == null) { 
            return "redirect:/login";
        }

        model.addAttribute("UID", UID);

        Pageable pageable = PageRequest.of(page - 1, 2);

        NasaApod apodToday = nasaApodService.getTodayApod();

        Page<NasaApod> apods = nasaApodService.getAllApods(pageable);

        model.addAttribute("apods", apods);

        return "/nasapictures";
    }
    
    
}

