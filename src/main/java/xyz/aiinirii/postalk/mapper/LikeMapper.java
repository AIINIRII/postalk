package xyz.aiinirii.postalk.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;
import xyz.aiinirii.postalk.bean.Like;

import java.util.List;

/**
 * @author AIINIRII
 */
@Mapper
@Repository
public interface LikeMapper {

    @Select("select * from `like` where tid = #{tid}")
    @Results(
            id = "findLikeByTId",
            value = {
                    @Result(id = true, column = "tid", property = "text", one = @One(select = "xyz.aiinirii.postalk.mapper.TextMapper.findTextById", fetchType = FetchType.LAZY)),
                    @Result(id = true, column = "uid", property = "user", one = @One(select = "xyz.aiinirii.postalk.mapper.UserMapper.findUserById", fetchType = FetchType.LAZY))
            }
    )
    List<Like> findLikeByTId(Integer tid);

    @Select("select * from `like` where tid = #{tid} && uid = #{uid}")
    @Results(
            id = "findLikeByTIdUId",
            value = {
                    @Result(id = true, column = "tid", property = "text", one = @One(select = "xyz.aiinirii.postalk.mapper.TextMapper.findTextById", fetchType = FetchType.LAZY)),
                    @Result(id = true, column = "uid", property = "user", one = @One(select = "xyz.aiinirii.postalk.mapper.UserMapper.findUserById", fetchType = FetchType.LAZY))
            }
    )
    Like findLikeByTIdUId(Integer tid, Integer uid);

    @Insert("insert into `like`(uid, tid) VALUES (#{uid}, #{tid})")
    int insertLike(Integer tid, Integer uid);

    @Delete("delete from `like` where uid = #{uid} && tid = #{tid}")
    int deleteLike(Integer tid, Integer uid);
}