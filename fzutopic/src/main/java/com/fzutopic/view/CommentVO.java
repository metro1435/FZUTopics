package com.fzutopic.view;


import com.fzutopic.model.Comment;
import com.fzutopic.model.Reply;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {
    Comment comment;
    String userName;
    List<ReplyVO> replies;

    public static CommentVO changeToCommentVO (Comment comment,
                                               String userName,List<ReplyVO> replies) {
        CommentVO commentVO=new CommentVO();
        commentVO.setComment(comment);
        commentVO.setReplies(replies);
        commentVO.setUserName(userName);
        return commentVO;
    }
}
