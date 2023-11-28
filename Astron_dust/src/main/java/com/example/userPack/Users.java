package com.example.userPack;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Users {
    @Id
    @Column(name = "UID")
    private String UID;
    private String password;
    
//    @Transient
//    private String checkPassword;  // 디비에 저장하지 않을 필드

    public Users() {   }

    public Users(String UID, String password) {//, String checkPassword) {
        this.UID = UID;
        this.password = password;
//        this.checkPassword = checkPassword;
    }
   

	public String getUID() {
        return UID;
    }

    public String getPassword() {
        return password;
    }

//    public String getCheckPassword() {
//        return checkPassword;
//    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public void setCheckPassword(String checkPassword) {
//        this.checkPassword = checkPassword;
//    }
}