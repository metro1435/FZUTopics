package com.fzutopic.view;

import com.fzutopic.model.Reply;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyVO {
    private String answerer;
    private Reply reply;

    public static ReplyVO changeToReplyVO(Reply reply,String answerer) {
        ReplyVO replyVO=new ReplyVO();
        replyVO.setAnswerer(answerer);
        replyVO.setReply(reply);
        return replyVO;
    }
}
