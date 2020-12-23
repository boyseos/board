package com.board.web.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BoardService {
	
	public Map<String, Object> stringToMap(String param){
		Function<String, Map<String, Object>> stm = x ->{
			Map<String, Object> map = new HashMap<String, Object>();
			try {
				map = new ObjectMapper().readValue(x, Map.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return map;
		};
		return stm.apply(param);
	}
	
	public List<Integer> decTobitArray(int num){
		List<Integer> list = new ArrayList<>();
		for(int i = 1; i <= num; i*=2) {
			if((i & num) != 0) {
				list.add(i);
			}
		}
		return list;
	}
	
	public Map<String, Object> webToDb(String param){
		Map<String, Object> map = stringToMap(param);
		map.put("boardKinds", decTobitArray((int)map.get("boardKind")));
		return map;
		
	}
	
}
