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
                    @Result(column = "uid", property = "user", one = @One(select = "xyz.aiinirii.postalk.mapper.UserMapper.findUserById", fetchType = FetchType.LAZY)),
                    @Result(id = true, column = "tid", property = "likes", many = @Many(select = "xyz.aiinirii.postalk.mapper.LikeMapper.findLikeByTId", fetchType = FetchType.LAZY))
            }
    )
    List<Post> findAllPost();

    @Insert("insert into post(tid) values (last_insert_id())")
    void insertPost(Post post);

    @Select("select * from post p left join text t on p.tid = t.id where uid = #{id} ORDER BY t.time DESC")
    @Results(id = "findAllPostByUId",
            value = {
                    @Result(id = true, column = "tid", property = "id"),
                    @Result(column = "content", property = "content"),
                    @Result(column = "time", property = "time"),
                    @Result(column = "uid", property = "user", one = @One(select = "xyz.aiinirii.postalk.mapper.UserMapper.findUserById", fetchType = FetchType.LAZY)),
                    @Result(id = true, column = "tid", property = "likes", many = @Many(select = "xyz.aiinirii.postalk.mapper.LikeMapper.findLikeByTId", fetchType = FetchType.LAZY))
            }
    )
    List<Post> findAllPostByUId(Integer id);

    @Select("select * from post left join text t on post.tid = t.id where tid = #{id}")
    @Results(id = "findPostById",
            value = {
                    @Result(id = true, column = "tid", property = "id"),
                    @Result(column = "content", property = "content"),
                    @Result(column = "time", property = "time"),
                    @Result(column = "uid", property = "user", one = @One(select = "xyz.aiinirii.postalk.mapper.UserMapper.findUserById", fetchType = FetchType.LAZY)),
                    @Result(id = true, column = "tid", property = "likes", many = @Many(select = "xyz.aiinirii.postalk.mapper.LikeMapper.findLikeByTId", fetchType = FetchType.LAZY))
            }
    )
    Post findPostById(Integer id);

    @Update("update post left join text t on post.tid = t.id set content=#{content} where tid=#{id}")
    int updatePost(Post post);

    @Delete("delete from post where tid = #{id}")
    int deletePostById(Integer id);
}