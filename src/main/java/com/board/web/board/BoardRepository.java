package com.board.web.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface BoardRepository {
	public List<BoardModel> getPage(Map<String, Object> param) throws Exception;
	public int boardCount(Map<String, Object> param) throws Exception;
	public void boardViewCountUp(@Param("seq") int seq) throws Exception;
	public BoardModel findByBoardSeq(@Param("seq") int seq) throws Exception;
	public void updateByBoardSeq(BoardModel board) throws Exception;
	public boolean insertBoard(BoardModel board) throws Exception;
}
