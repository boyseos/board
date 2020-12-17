package com.board.web.board;

import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository {
	public Map<String, Object> findAll() throws Exception;
	public Map<String, Object> findBoardSeq() throws Exception;
}
