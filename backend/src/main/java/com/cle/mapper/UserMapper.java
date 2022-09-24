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
     *
     * @param userName
     * @return
     */
    @Select("SELECT  * FROM USER WHERE userName = #{userName}")
    public User selectUserByUsername(String userName);

    /**
     * 添加用户
     * @param user
     */
    @Insert("insert into user values (#{uid},#{userName},#{password},#{age},#{realName},#{cancelCount},#{isAllow})")
    public void add(User user);
}
