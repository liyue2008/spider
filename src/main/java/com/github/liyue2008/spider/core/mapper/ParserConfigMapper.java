package com.github.liyue2008.spider.core.mapper;

import com.github.liyue2008.spider.core.entity.ParserConfig;
import com.github.liyue2008.spider.core.entity.ParserConfigExample;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
@Mapper
public interface ParserConfigMapper {
    @SelectProvider(type=ParserConfigSqlProvider.class, method="countByExample")
    int countByExample(ParserConfigExample example);

    @DeleteProvider(type=ParserConfigSqlProvider.class, method="deleteByExample")
    int deleteByExample(ParserConfigExample example);

    @Delete({
        "delete from PARSER_CONFIG",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into PARSER_CONFIG (NAME, TYPE, ",
        "LIST_ITEM_SELECTOR, PREVIEW_URL, ",
        "USER_AGENT, REFERER, ",
        "TIMEOUT, REMARK, ",
        "DEL_FLAG)",
        "values (#{name,jdbcType=VARCHAR}, #{type,jdbcType=INTEGER}, ",
        "#{listItemSelector,jdbcType=VARCHAR}, #{previewUrl,jdbcType=VARCHAR}, ",
        "#{userAgent,jdbcType=VARCHAR}, #{referer,jdbcType=VARCHAR}, ",
        "#{timeout,jdbcType=INTEGER}, #{remark,jdbcType=VARCHAR}, ",
        "#{delFlag,jdbcType=INTEGER})"
    })
    @SelectKey(statement="CALL IDENTITY()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(ParserConfig record);

    @InsertProvider(type=ParserConfigSqlProvider.class, method="insertSelective")
    @SelectKey(statement="CALL IDENTITY()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(ParserConfig record);

    @SelectProvider(type=ParserConfigSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="NAME", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="TYPE", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="LIST_ITEM_SELECTOR", property="listItemSelector", jdbcType=JdbcType.VARCHAR),
        @Result(column="PREVIEW_URL", property="previewUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="USER_AGENT", property="userAgent", jdbcType=JdbcType.VARCHAR),
        @Result(column="REFERER", property="referer", jdbcType=JdbcType.VARCHAR),
        @Result(column="TIMEOUT", property="timeout", jdbcType=JdbcType.INTEGER),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEL_FLAG", property="delFlag", jdbcType=JdbcType.INTEGER)
    })
    List<ParserConfig> selectByExample(ParserConfigExample example);

    @Select({
        "select",
        "ID, NAME, TYPE, LIST_ITEM_SELECTOR, PREVIEW_URL, USER_AGENT, REFERER, TIMEOUT, ",
        "REMARK, DEL_FLAG",
        "from PARSER_CONFIG",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="NAME", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="TYPE", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="LIST_ITEM_SELECTOR", property="listItemSelector", jdbcType=JdbcType.VARCHAR),
        @Result(column="PREVIEW_URL", property="previewUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="USER_AGENT", property="userAgent", jdbcType=JdbcType.VARCHAR),
        @Result(column="REFERER", property="referer", jdbcType=JdbcType.VARCHAR),
        @Result(column="TIMEOUT", property="timeout", jdbcType=JdbcType.INTEGER),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEL_FLAG", property="delFlag", jdbcType=JdbcType.INTEGER)
    })
    ParserConfig selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ParserConfigSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ParserConfig record, @Param("example") ParserConfigExample example);

    @UpdateProvider(type=ParserConfigSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ParserConfig record, @Param("example") ParserConfigExample example);

    @UpdateProvider(type=ParserConfigSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ParserConfig record);

    @Update({
        "update PARSER_CONFIG",
        "set NAME = #{name,jdbcType=VARCHAR},",
          "TYPE = #{type,jdbcType=INTEGER},",
          "LIST_ITEM_SELECTOR = #{listItemSelector,jdbcType=VARCHAR},",
          "PREVIEW_URL = #{previewUrl,jdbcType=VARCHAR},",
          "USER_AGENT = #{userAgent,jdbcType=VARCHAR},",
          "REFERER = #{referer,jdbcType=VARCHAR},",
          "TIMEOUT = #{timeout,jdbcType=INTEGER},",
          "REMARK = #{remark,jdbcType=VARCHAR},",
          "DEL_FLAG = #{delFlag,jdbcType=INTEGER}",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ParserConfig record);
}