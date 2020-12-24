package com.board.web.board;

import java.io.Serializable;
import java.util.Date;

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
public class BoardModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int boardSeq;
	private String boardType;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private Date boardDate;
	private int boardViewCount;
	private int boardVote;
}
