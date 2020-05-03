package com.fzutopic.dao;

import com.fzutopic.model.Favlist;
import com.fzutopic.model.FavlistExample;
import java.util.List;

import org.apache.ibatis.annotations.*;

public interface FavlistDao {
    long countByExample(FavlistExample example);

    int deleteByExample(FavlistExample example);

    int deleteByPrimaryKey(String favlistid);

    int insert(Favlist record);

    int insertSelective(Favlist record);

    List<Favlist> selectByExample(FavlistExample example);

    /*
     * 一对多查询
     * property：查询结果赋值给此实体属性
     * column：对应数据库的表字段，可做为下面@One(select方法)的查询参数
     * many：一对多的查询
     * @Many(select = 方法全路径) ：调用的方法
     * @author 403
     */
    @Results({
            @Result(property = "favlistid", column = "favlistID"),//加此行，否则id值为空
            @Result(property = "favlistItemKeys", column = "collectedid",
                    many = @Many(select = "com.fzutopic.dao.FavlistItemDao.getFavlistItemByFavlistid"))
    })


    Favlist selectByPrimaryKey(String favlistid);

    @Insert("insert into favlist(favlistID,userID,name,time) values (#{favlistid},#{userid},#{name},#{time})")
    int insertByFavlist(Favlist favlist);

    int updateByExampleSelective(@Param("record") Favlist record, @Param("example") FavlistExample example);

    int updateByExample(@Param("record") Favlist record, @Param("example") FavlistExample example);

    int updateByPrimaryKeySelective(Favlist record);

    int updateByPrimaryKey(Favlist record);
}