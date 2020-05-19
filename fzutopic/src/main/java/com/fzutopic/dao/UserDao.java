package com.fzutopic.dao;

import com.fzutopic.model.User;
import com.fzutopic.model.UserExample;
import java.util.List;

import org.apache.ibatis.annotations.*;

public interface UserDao {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(String userid);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExampleWithBLOBs(UserExample example);

    List<User> selectByExample(UserExample example);

    /*
     * 一对多查询
     * property：查询结果赋值给此实体属性
     * column：对应数据库的表字段，可做为下面@One(select方法)的查询参数
     * many：一对多的查询
     * @Many(select = 方法全路径) ：调用的方法
     * @author 403
     */
    @Results({
            @Result(property = "userid", column = "userID"),//加此行，否则id值为空
            @Result(property = "college", column = "college"),
            @Result(property = "favlists", column = "favlistid",
                    many = @Many(select = "com.fzutopic.dao.FavlistDao.getFavlistByuserid"))
    })

    User selectByPrimaryKey(String userid);

    //221701416
    //根据学院搜学生列表
    List<User> selectByCollege(String college);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExampleWithBLOBs(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);

}