package com.board.web.reply;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReplyModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int replyNo;
	private String replyContent;
	private int boardSeq;
	private String userId;
	private int replyDepth;
	private int inReplyNo;
	
}
