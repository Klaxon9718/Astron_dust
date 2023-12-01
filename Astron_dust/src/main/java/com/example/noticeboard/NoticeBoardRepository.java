package com.example.noticeboard;

import org.springframework.data.jpa.repository.JpaRepository;


// JpaRepository를 상속받은 것으로 DB에 접근하여 CRUD작업을 수행하는 역할을 함
// save(), findAll(), findById(), delete() 등과 같은 메서드를 제공
// 직업 SQL쿼리를 작성하지 않아도 CRUD작업을 수행할 수 있음
// JpaRepository를 상속할 땐 두개의 제네릭 타입을 지정
// - 첫번째 : Entity클래스를 지정, 두번째 : 기본 키의 타입을 지정
// 이 인터페이스를 서비스 클래스나 컨트롤러에서 주입받아 사용하면, 복잡한 Sql 쿼리 없이도 DB작업을 수행할 수 있다.
public interface NoticeBoardRepository extends JpaRepository<NoticeBoardModel, Integer>{

}
