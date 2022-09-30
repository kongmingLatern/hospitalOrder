package com.cle.mapper;
import com.cle.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    /**
     * 查询所有用户
     *
     * @return list<user></>
     */
    @Select("SELECT * FROM USER")
    List<User> selectAll();

    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return User 查询到的用户
     */
    @Select("SELECT  * FROM USER WHERE userName = #{userName}")
    User selectUserByUsername(String userName);

    /**
     * 添加用户
     *
     * @param user
     * @return int 添加的用户条数
     */
    @Insert("insert into user values (#{uid},#{userName},#{password},#{age},#{realName},#{cancelCount},#{isAllow},#{isAuth})")
    int add(User user);

    /**
     * 分页查询
     *
     * @param begin
     * @param size
     * @return List<User>
     */
    @Select("SELECT * FROM USER LIMIT #{begin} , #{size}")
    List<User> selectByPage(@Param("begin") int begin, @Param("size") int size);

    /**
     * 查询所有用户条数
     *
     * @return int 用户条数
     */
    @Select("select count(*) from user")
    int count();

    /**
     * 删除用户
     *
     * @param uid
     * @return int 删除的条数
     */
    @Delete("delete from user where uid = #{uid}")
    int delete(@Param("uid") String uid);

    /**
     * 根据uid查询用户
     *
     * @param uid
     * @return
     */
    @Select("select * from user where uid=#{uid}")
    User selectByUid(String uid);

    @Update("UPDATE USER SET userName=#{userName},password=#{password},age=#{age},realName=#{realName},cancelCount=#{cancelCount},isAllow==#{isAllow},isAuth=#{isAuth} WHERE uid=#{uid}")
    int change(User user);
}
