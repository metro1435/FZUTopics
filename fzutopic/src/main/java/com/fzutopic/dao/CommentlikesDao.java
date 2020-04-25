package com.fzutopic.dao;

import com.fzutopic.model.Commentlikes;
import com.fzutopic.model.CommentlikesExample;
import com.fzutopic.model.CommentlikesKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommentlikesDao {
    long countByExample(CommentlikesExample example);

    int deleteByExample(CommentlikesExample example);

    int deleteByPrimaryKey(CommentlikesKey key);

    int insert(Commentlikes record);

    int insertSelective(Commentlikes record);

    List<Commentlikes> selectByExample(CommentlikesExample example);

    Commentlikes selectByPrimaryKey(CommentlikesKey key);

    int updateByExampleSelective(@Param("record") Commentlikes record, @Param("example") CommentlikesExample example);

    int updateByExample(@Param("record") Commentlikes record, @Param("example") CommentlikesExample example);

    int updateByPrimaryKeySelective(Commentlikes record);

    int updateByPrimaryKey(Commentlikes record);
}