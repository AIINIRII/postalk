package xyz.aiinirii.postalk.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;
import xyz.aiinirii.postalk.bean.Comment;
import xyz.aiinirii.postalk.bean.Text;
import xyz.aiinirii.postalk.bean.User;

import java.util.List;

/**
 * @author AIINIRII
 */
@Mapper
@Repository
public interface CommentMapper {

    @Insert("insert into comment(tid, pid) VALUES (last_insert_id(), #{pid})")
    void insertComment(Integer pid);

    @Select("select * from comment left join text t on comment.tid = t.id where pid = #{tid}")
    @Results(
            id = "findAllCommentByPId",
            value = {
                    @Result(id = true, column = "tid", property = "id"),
                    @Result(column = "content", property = "content"),
                    @Result(column = "time", property = "time"),
                    @Result(column = "uid", property = "user", one = @One(select = "xyz.aiinirii.postalk.mapper.UserMapper.findUserById", fetchType = FetchType.LAZY)),
                    @Result(id = true, column = "tid", property = "likes", many = @Many(select = "xyz.aiinirii.postalk.mapper.LikeMapper.findLikeByTId", fetchType = FetchType.LAZY)),
                    @Result(id = true, column = "tid", property = "comments", many = @Many(select = "xyz.aiinirii.postalk.mapper.CommentMapper.findAllCommentByPId",fetchType = FetchType.LAZY)),
                    @Result(id = true, column = "pid", property = "text", one = @One(select = "xyz.aiinirii.postalk.mapper.TextMapper.findTextById", fetchType = FetchType.LAZY))
            }
    )
    List<Comment> findAllCommentByPId(Integer tid);

    @Delete("delete from comment where tid = #{id}")
    int deleteCommentById(Integer id);

    @Select("select * from comment left join text t on comment.tid = t.id where id = #{id}")
    @Results(id = "findCommentById",
            value = {
                    @Result(id = true, column = "tid", property = "id"),
                    @Result(column = "content", property = "content"),
                    @Result(column = "time", property = "time"),
                    @Result(column = "uid", property = "user", one = @One(select = "xyz.aiinirii.postalk.mapper.UserMapper.findUserById", fetchType = FetchType.LAZY)),
                    @Result(id = true, column = "tid", property = "likes", many = @Many(select = "xyz.aiinirii.postalk.mapper.LikeMapper.findLikeByTId", fetchType = FetchType.LAZY)),
                    @Result(id = true, column = "tid", property = "comments", many = @Many(select = "xyz.aiinirii.postalk.mapper.CommentMapper.findAllCommentByPId", fetchType = FetchType.LAZY)),
                    @Result(id = true, column = "pid", property = "text", one = @One(select = "xyz.aiinirii.postalk.mapper.TextMapper.findTextById", fetchType = FetchType.LAZY))
            }
    )
    Comment findCommentById(Integer id);
}