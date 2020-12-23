package com.board.web.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/board")
public class BoardController {
	@Autowired BoardRepository boardRepository;
	@Autowired BoardService boardService;
	
	@GetMapping("/param={param}")
	public List<BoardModel> getPage(@PathVariable String param) throws Exception {
		Map<String, Object> map = boardService.webToDb(param);
		System.out.println("getpage =" + map.toString());
		return boardRepository.getPage(map);
	}
	
	@GetMapping("/count/param={param}")
	public int boardCount(@PathVariable String param) throws Exception {
		int value = boardRepository.boardCount(boardService.webToDb(param));
		System.out.println("boardCount ="+value);
		return value;
	}
	
	@GetMapping("/seq={seq}")
	public BoardModel getBoard(@PathVariable int seq) throws Exception {
		//boardRepository.boardViewCountUp(seq);
		return boardRepository.findByBoardSeq(seq);
	}
	
	@PatchMapping("/patch")
	public void updateBoard(@RequestBody BoardModel board) throws Exception {
		boardRepository.updateByBoardSeq(board);
	}
	@PostMapping("/write")
	public boolean boardWrite(@RequestBody BoardModel board) throws Exception{
		System.out.println("글쓰기 받음 = "+board.toString());
		return boardRepository.insertBoard(board);
	}
}
