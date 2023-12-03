package com.example.noticeboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NoticeBoardService {
	private final NoticeBoardRepository repository;
	@Autowired
	public NoticeBoardService(NoticeBoardRepository repository) {
		this.repository = repository;
	}
	
	//데이터 삽입
	public NoticeBoardModel insertNotice(String writer, String title, String content) {
		NoticeBoardModel noticeBoard = new NoticeBoardModel();
        noticeBoard.setWriter(writer);
        noticeBoard.setTitle(title);
        noticeBoard.setContent(content);
		
		return repository.save(noticeBoard); //저장된 엔티티를 반환
	}
	
	//데이터 조회
	public List<NoticeBoardModel> getAllNotices() {
        return repository.findAll(); // 모든 게시글 불러오기
    }
	
	//페이지에 해당하는 게시글 반환
	public Page<NoticeBoardModel> getNotices(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
