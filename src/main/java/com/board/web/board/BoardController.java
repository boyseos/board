package com.board.web.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/board")
public class BoardController {
	@Autowired BoardRepository boardRepository;
	
	@GetMapping("/page={page}")
	public List<BoardModel> getAll(@PathVariable int page) throws Exception {
		return boardRepository.findAll();
	}
	
	@GetMapping("/count")
	public int boardCount() throws Exception {
		return boardRepository.boardCount();
	}
	
	@PostMapping("/write")
	public boolean boardWrite(BoardModel board) throws Exception{
		
		return false;
	}

}
