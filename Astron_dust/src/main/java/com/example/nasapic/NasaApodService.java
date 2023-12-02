package com.example.nasapic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
            String formattedDate = today.format(DateTimeFormatter.ISO_DATE);
            String apiUrl = "https://api.nasa.gov/planetary/apod?api_key=" + apiKey + "&date=" + formattedDate;

            RestTemplate restTemplate = new RestTemplate();
            apodToday = restTemplate.getForObject(apiUrl, NasaApod.class);

            if (apodToday != null) {
                nasaApodRepository.save(apodToday);
            }
        }

        return apodToday;
    }

    public List<NasaApod> getAllApods() {
        return nasaApodRepository.findAll();
    }

    public List<NasaApod> getApodsForDateRange() {
        LocalDate startDate = LocalDate.of(2023, 11, 1);
        LocalDate endDate = LocalDate.now();

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

