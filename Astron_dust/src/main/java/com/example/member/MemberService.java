package com.example.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;


@Service
public class MemberService {
	
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void signUp(String UID, String password, String checkPassword) {
        
        boolean flag = true;
        if (memberRepository.findByUID(UID).isPresent()) {
        	flag = false;
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }

        else if (!password.equals(checkPassword)) {
        	flag = false;
            throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
        }
        if(flag) {
        Member member = new Member(UID, password);
        member.setUID(UID);
        member.setPassword(password);
        System.out.println("userRepository.save(member) 실행");
        // 값 저장
        memberRepository.save(member);
        }
    }
        public boolean checkUID(String UID) {
            return memberRepository.findByUID(UID).isPresent();
        }
}
