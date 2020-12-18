package com.board.web.board;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository {
	public List<BoardModel> findAll() throws Exception;
	public int boardCount() throws Exception;
	public Map<String, Object> findBoardSeq() throws Exception;
}
