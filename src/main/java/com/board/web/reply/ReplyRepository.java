package com.board.web.reply;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository {
	public List<ReplyModel> getReplyList(@Param("seq") int seq) throws Exception;
	public void insertReply(ReplyModel reply) throws Exception;
}
