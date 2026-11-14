package com.lyn.dao;

import com.lyn.entity.word;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface wordDao {
    @Select("select * from word")
    List<word> getAllWord();
    @Select("select * from word where wordId= #{wordId}")
    word getWord(@Param("wordId") Integer wordId);

    @Delete("delete from word where wordId= #{wordId}")
    int delWord(@Param("wordId") Integer wordId);

    @Insert("insert into word (saying) values(#{saying})")
    @Options(useGeneratedKeys = true, keyProperty = "wordId")
    int insert(word word);

    @Update("update word SET wordId= #{wordId},saying= #{saying} " +
            "where wordId= #{wordId}")
    int update(word word);
}
