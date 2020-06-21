package xyz.aiinirii.postalk.mapper;

import org.apache.ibatis.annotations.*;
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
    Text findTextById(Integer id);

    @Delete("delete from text where id=#{id}")
    int deleteTextById(Integer id);
}
