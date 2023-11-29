package com.example.member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Member {
	@Id
    private String UID;
    @Column(name = "password")
    private String password;
    
    @Transient
    private String checkPassword;  // 디비에 저장하지 않을 필드
    

    public Member() {   }

    public Member(String UID, String password) {
        this.UID = UID;
        this.password = password;
    }
    
	public Member(String UID, String password, String checkPassword) {
		this.UID = UID;
		this.password = password;
		this.checkPassword = checkPassword;
	}

	public String getUID() {
        return UID;
    }

    public String getPassword() {
        return password;
    }

    public String getCheckPassword() {
		return checkPassword;
	}


	public void setUID(String UID) {
        this.UID = UID;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}

	@Override
	public String toString() {
		return "Member [UID=" + UID + ", password=" + password + "]";
	}
    
    

}
