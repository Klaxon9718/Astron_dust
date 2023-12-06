package com.example.nasapic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class NasaApodService {

    @Value("${nasa-open-api}")
    private String apiKey;

    private final NasaApodRepository nasaApodRepository;

    public NasaApodService(NasaApodRepository nasaApodRepository) {
        this.nasaApodRepository = nasaApodRepository;
    }

    public NasaApod getTodayApod() {
        LocalDate today = LocalDate.now();
        NasaApod apodToday = nasaApodRepository.findByDate(today);

        if (apodToday == null) {
            try {
                String formattedDate = today.format(DateTimeFormatter.ISO_DATE);
                String apiUrl = "https://api.nasa.gov/planetary/apod?api_key=" + apiKey + "&date=" + formattedDate;

                RestTemplate restTemplate = new RestTemplate();
                apodToday = restTemplate.getForObject(apiUrl, NasaApod.class);

                if (apodToday != null) {
                    nasaApodRepository.save(apodToday);
                }
            } catch (Exception e) {
                // API 호출에 실패했을 경우, 디비에 저장된 최신 사진을 반환
                apodToday = nasaApodRepository.findTopByOrderByDateDesc();
            }
        }

            getApodsForDateRange(today.minusDays(2)); //이틀전 
            apodToday = nasaApodRepository.findTopByOrderByDateDesc();


        return apodToday;
    }


    public Page<NasaApod> getAllApods(Pageable pageable) {
        return nasaApodRepository.findAll(pageable);
    }


    public List<NasaApod> getApodsForDateRange(LocalDate endDate) { // 'endDate' 파라미터 추가
        LocalDate startDate = LocalDate.of(2023, 11, 1);

        List<NasaApod> apods = new ArrayList<>();

        while (!startDate.isAfter(endDate)) {
            String formattedDate = startDate.format(DateTimeFormatter.ISO_DATE);
            String apiUrl = "https://api.nasa.gov/planetary/apod?api_key=" + apiKey + "&date=" + formattedDate;

            if (!isDateAlreadyStored(startDate)) {
                RestTemplate restTemplate = new RestTemplate();
                NasaApod apod = restTemplate.getForObject(apiUrl, NasaApod.class);

                if (apod != null) {
                    apods.add(apod);
                    nasaApodRepository.save(apod);
                }
            }

            startDate = startDate.plusDays(1);
        }

        return apods;
    }

    private boolean isDateAlreadyStored(LocalDate date) {
        return nasaApodRepository.existsByDate(date);
    }
}
