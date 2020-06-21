package xyz.aiinirii.postalk.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;
import xyz.aiinirii.postalk.bean.Friend;

import java.util.Date;
import java.util.List;

/**
 * @author AIINIRII
 */
@Mapper
@Repository
public interface FriendMapper {

    @Select("select * from friend where uid_1 = #{id} or uid_2 = #{id}")
    @Results(id = "findAllFriendsByUId",
            value = {
                    @Result(id = true, column = "uid_1", property = "user1", one = @One(select = "xyz.aiinirii.postalk.mapper.UserMapper.findUserById", fetchType = FetchType.LAZY)),
                    @Result(id = true, column = "uid_2", property = "user2", one = @One(select = "xyz.aiinirii.postalk.mapper.UserMapper.findUserById", fetchType = FetchType.LAZY)),
                    @Result(column = "since_time", property = "sinceTime")
            })
    List<Friend> findAllFriendsByUId(Integer id);

    @Insert("insert into friend(uid_1, uid_2, since_time) VALUES (#{id1}, #{id2}, #{sinceTime})")
    void insertFriendByUId(Integer id1, Integer id2, Date sinceTime);

    @Delete("delete from friend where (uid_1=#{id} and uid_2=#{id1}) or (uid_2=#{id} and uid_1=#{id1})")
    int deleteFriendById(Integer id, Integer id1);
}