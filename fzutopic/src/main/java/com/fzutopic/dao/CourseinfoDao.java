package com.fzutopic.dao;

import com.fzutopic.model.Courseinfo;
import com.fzutopic.model.CourseinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseinfoDao {
    long countByExample(CourseinfoExample example);

    int deleteByExample(CourseinfoExample example);

    int deleteByPrimaryKey(String courseid);

    int insert(Courseinfo record);

    int insertSelective(Courseinfo record);

    List<Courseinfo> selectByExample(CourseinfoExample example);

    Courseinfo selectByPrimaryKey(String courseid);

    int updateByExampleSelective(@Param("record") Courseinfo record, @Param("example") CourseinfoExample example);

    int updateByExample(@Param("record") Courseinfo record, @Param("example") CourseinfoExample example);

    int updateByPrimaryKeySelective(Courseinfo record);

    int updateByPrimaryKey(Courseinfo record);
}