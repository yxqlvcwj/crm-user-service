package com.dwd.www.db.mapper;

import com.dwd.www.db.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    User findUserById(@Param("id") String id);

    /**
     * 添加用户
     * @param user
     * @return
     * @throws Exception
     */
    @Insert("insert into user(user_name,password,age,sex,permission,is_delete) " +
            "values(#{user.userName},#{user.password},#{user.age},#{user.sex},#{user.permission},#{user.isDelete})")
    int addUser(@Param("user") User user) throws Exception;

    /**
     * 获取用户详情
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    User findUserInfoById(@Param("id") String id);

    /**
     * 获取用户列表
     * @return
     */
    @Select("select * from user")
    List<User> getUserList();

    /**
     * 用户登录检查
     * @param userName
     * @param password
     * @return
     */
    @Select("select count(*) from user where user_name = #{userName} and password=#{password}")
    int UserLogin(@Param("userName") String userName, @Param("password") String password);

    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    @Select("select * from user where user_name = #{userName} and password=#{password}")
    User getUserInfo(@Param("userName") String userName, @Param("password") String password);


    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @Update("update user set user_name=#{user.userName},password=#{user.password} where id=#{user.id}")
    int updateUserInfo(@Param("user") User user);

}