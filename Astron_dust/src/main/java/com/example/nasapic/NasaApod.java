package com.example.nasapic;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Data
@Entity
public class NasaApod {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PID")
	private Long PID;
	
	@Column(length = 1000)
	private String title;
	@Lob
	private String explanation;
	@Column(length = 1000)
	private String url;
	
	@Column(columnDefinition = "DATE")
	private LocalDate date;
	private String media_type;
	

}
