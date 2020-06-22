package xyz.aiinirii.postalk.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;
import xyz.aiinirii.postalk.bean.Text;

/**
 * @author AIINIRII
 */
@Mapper
@Repository
public interface TextMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into text(uid, content, time, anonymous) values(#{user.id}, #{content}, #{time}, #{anonymous})")
    void insertText(Text text);

    @Select("select * from text where id = #{id}")
    @Results(id = "findTextById",
            value = {
                    @Result(id = true, column = "id", property = "id"),
                    @Result(column = "content", property = "content"),
                    @Result(column = "time", property = "time"),
                    @Result(column = "uid", property = "user", one = @One(select = "xyz.aiinirii.postalk.mapper.UserMapper.findUserById", fetchType = FetchType.LAZY)),
                    @Result(id = true, column = "id", property = "likes", many = @Many(select = "xyz.aiinirii.postalk.mapper.LikeMapper.findLikeByTId", fetchType = FetchType.LAZY)),
                    @Result(id = true, column = "id", property = "comments", many = @Many(select = "xyz.aiinirii.postalk.mapper.CommentMapper.findAllCommentByPId", fetchType = FetchType.LAZY))
            }
    )
    Text findTextById(Integer id);

    @Delete("delete from text where id = #{id}")
    int deleteTextById(Integer id);
}
