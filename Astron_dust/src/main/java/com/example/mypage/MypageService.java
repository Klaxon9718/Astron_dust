package com.example.mypage;

import com.example.noticeboard.NoticeBoardModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MypageService {
    @Autowired
    private MypageRepository mypageRepository;

    public List<NoticeBoardModel> getUserPosts(String UID) {
        return mypageRepository.findByWriter(UID); // 사용자가 작성한 게시글 검색
    }
}
