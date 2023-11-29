package com.example.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, String> {
	
	//ID라는 문자열을 인자로 받아서 해당 UID를 가진 Users 객체를 데이터베이스에서 찾아서 반환
	/*
	 * 반환 타입이 Optional<Users>인 이유는 결과값이 존재하지 않을 수도 있기 때문입니다.
	 * 즉, UID에 해당하는 Users 객체가 데이터베이스에 없을 경우 null을 반환하는 대신 Optional.empty()를 반환하여,
	 * 이후 코드에서 Null Pointer Exception을 방지할 수 있습니다.
	 */
	Optional<Member> findByUID(String UID);
	
}

