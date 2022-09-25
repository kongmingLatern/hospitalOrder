package com.cle.mapper;
import com.cle.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
     * @return
     */
    @Select("SELECT  * FROM USER WHERE userName = #{userName}")
    User selectUserByUsername(String userName);

    /**
     * 添加用户
     *
     * @param user
     */
    @Insert("insert into user values (#{uid},#{userName},#{password},#{age},#{realName},#{cancelCount},#{isAllow})")
    int add(User user);

    /**
     * 分页查询
     *
     * @param begin
     * @param size
     * @return
     */
    @Select("SELECT * FROM USER LIMIT #{begin} , #{size}")
    List<User> selectByPage(@Param("begin") int begin, @Param("size") int size);

    /**
     * 查询所有用户条数
     *
     * @return
     */
    @Select("select count(*) from user")
    int count();

    /**
     * 删除用户
     *
     * @param uid
     */
    @Delete("delete from user where uid = #{uid}")
    int delete(@Param("uid") String uid);
}
