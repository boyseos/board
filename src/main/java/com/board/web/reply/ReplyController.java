package com.board.web.reply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/comment")
@CrossOrigin(origins = "*")
@RestController
public class ReplyController {
	@Autowired ReplyRepository replyRepository;
	
	@GetMapping("seq={seq}")
	public List<ReplyModel> getReply(@PathVariable int seq) throws Exception{
		List<ReplyModel> temp = replyRepository.getReplyList(seq);
		System.out.println("보낼덧글 = "+temp.toString());
		return temp;
	}
	
	@PostMapping
	public int replyWrite(@RequestBody ReplyModel reply) throws Exception{
		System.out.println("덧글받음 = "+reply.toString());
		replyRepository.insertReply(reply);
		
		return 1;
	}
}
