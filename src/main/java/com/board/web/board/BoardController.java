package com.board.web.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/board")
public class BoardController {
	@Autowired BoardRepository boardRepository;
	
	@GetMapping("/page={page}&pageSize={pageSize}")
	public List<BoardModel> getPage(@PathVariable int page, @PathVariable int pageSize) throws Exception {
		return boardRepository.getPage((page-1)*pageSize,pageSize);
	}
	
	@GetMapping("/count")
	public int boardCount() throws Exception {
		return boardRepository.boardCount();
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
		return boardRepository.insertBoard(board);
	}

}
