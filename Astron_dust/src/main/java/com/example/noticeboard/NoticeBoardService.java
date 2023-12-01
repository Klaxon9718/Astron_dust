package com.example.noticeboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeBoardService {
	private final NoticeBoardRepository repository;
	@Autowired
	public NoticeBoardService(NoticeBoardRepository repository) {
		this.repository = repository;
	}
	
	public NoticeBoardModel insertNotice(String writer, String title, String content) {
		NoticeBoardModel noticeBoard = new NoticeBoardModel();
        noticeBoard.setWriter(writer);
        noticeBoard.setTitle(title);
        noticeBoard.setContent(content);
		
		return repository.save(noticeBoard); //저장된 엔티티를 반환
	}
	
	public List<NoticeBoardModel> getAllNotices() {
        return repository.findAll(); // 모든 게시글 불러오기
    }
}
