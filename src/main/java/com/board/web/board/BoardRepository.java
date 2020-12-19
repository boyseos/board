package com.board.web.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository {
	public List<BoardModel> findAll() throws Exception;
	public List<BoardModel> getPage(@Param("page") int page, @Param("pageSize")int pageSize) throws Exception;
	public int boardCount() throws Exception;
	public boolean insertBoard(BoardModel board) throws Exception;
	public Map<String, Object> findBoardSeq() throws Exception;
}
