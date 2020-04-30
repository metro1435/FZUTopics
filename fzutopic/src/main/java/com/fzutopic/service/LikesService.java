package com.fzutopic.service;

import com.fzutopic.model.Commentlikes;
import com.fzutopic.model.CommentlikesKey;
import com.fzutopic.model.Topiclikes;
import com.fzutopic.model.TopiclikesKey;

public interface LikesService {
    //删除话题点赞（踩）记录
    void deleteTopiclikes(TopiclikesKey key);

    //修改话题点赞（踩）记录
    Topiclikes updateTopicLikes(Topiclikes topiclikes);

    //新增话题点赞（踩）记录
    Topiclikes postTopicLikes(Topiclikes topiclikes);

    //删除评论点赞（踩）记录
    void deleteCommentlikes(CommentlikesKey key);

    //修改评论点赞（踩）记录
    Commentlikes updateCommentLikes(Commentlikes topiclikes);

    //新增评论点赞（踩）记录
    Commentlikes postCommentLikes(Commentlikes topiclikes);
}
