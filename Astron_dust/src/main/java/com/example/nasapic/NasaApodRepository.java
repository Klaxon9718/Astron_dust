package com.example.nasapic;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface NasaApodRepository extends JpaRepository<NasaApod, Long> {

    boolean existsByDate(LocalDate date);
    
    NasaApod findByDate(LocalDate date);

}
