package com.cle.mapper;

import com.cle.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    /**
     * 查询所有用户
     * @return list<user></>
     */
    @Select("SELECT * FROM USER")
    public List<User> selectAll();

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @Select("SELECT  * FROM USER WHERE username = #{username}")
    public User selectUserByUsername(String username);

    /**
     * 添加用户
     * @param user
     */
    @Insert("insert into user values (null,#{user.username},#{user.password},#{user.name},#{user.sex})")
    public void add(User user);
}
