package xyz.aiinirii.postalk.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;
import xyz.aiinirii.postalk.bean.Post;

import java.util.List;

/**
 * @author AIINIRII
 */
@Mapper
@Repository
public interface PostMapper {

    @Select("select * from post p left join text t on p.tid = t.id ORDER BY t.time DESC")
    @Results(id = "findAllPost",
            value = {
                    @Result(id = true, column = "tid", property = "id"),
                    @Result(column = "content", property = "content"),
                    @Result(column = "time", property = "time"),
                    @Result(column = "uid", property = "user", one = @One(select = "xyz.aiinirii.postalk.mapper.UserMapper.findUserById", fetchType = FetchType.LAZY))
            }
    )
    List<Post> findAllPost();

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into text(uid, content, time, anonymous) values(#{user.id}, #{content}, #{time}, #{anonymous})")
    void insertText(Post post);

    @Insert("insert into post(tid) values (last_insert_id())")
    void insertPost(Post post);
}