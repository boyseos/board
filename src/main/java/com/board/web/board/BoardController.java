package com.board.web.board;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/board")
public class BoardController {
	@Autowired BoardRepository boardRepository;
	
	@GetMapping("/start")
	public Map<?, ?> startMessage() {
		Supplier<Map<?, ?>> selectAll = () -> {
			Map<String, Object> map = new HashMap<String, Object>();
			try {
				map = boardRepository.findAll();
			} catch (Exception e) {
				e.printStackTrace();
				map.put("error",e.toString());
			}
			return map;
		};
		return selectAll.get();
	}

}
