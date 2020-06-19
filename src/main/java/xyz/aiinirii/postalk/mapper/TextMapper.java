package xyz.aiinirii.postalk.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
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

    @Delete("delete from text where id=#{id}")
    int deleteTextById(Integer id);
}
