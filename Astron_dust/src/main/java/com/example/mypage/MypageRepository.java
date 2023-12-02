package com.example.mypage;

import com.example.noticeboard.NoticeBoardModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MypageRepository extends JpaRepository<NoticeBoardModel, Integer> {
    List<NoticeBoardModel> findByWriter(String writer); // writer 필드를 기준으로 게시글 검색

}
