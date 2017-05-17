package com.github.liyue2008.spider.core.mapper;

import com.github.liyue2008.spider.core.entity.ParserConfigItem;
import com.github.liyue2008.spider.core.entity.ParserConfigItemExample;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
@Mapper
public interface ParserConfigItemMapper {
    @SelectProvider(type=ParserConfigItemSqlProvider.class, method="countByExample")
    int countByExample(ParserConfigItemExample example);

    @DeleteProvider(type=ParserConfigItemSqlProvider.class, method="deleteByExample")
    int deleteByExample(ParserConfigItemExample example);

    @Delete({
        "delete from PARSER_CONFIG_ITEM",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into PARSER_CONFIG_ITEM (PARSER_CONFIG_ID, SUB_CONFIG_ID, ",
        "COLUMN_TITLE, SELECTOR, ",
        "DOM_INDEX, ATTRIBUTE, ",
        "SEPARATOR, PATTERN, ",
        "PATTERN_INDEX, ITEM_TYPE, ",
        "ITEM_INDEX, REMARK, ",
        "DEL_FLAG)",
        "values (#{parserConfigId,jdbcType=INTEGER}, #{subConfigId,jdbcType=INTEGER}, ",
        "#{columnTitle,jdbcType=VARCHAR}, #{selector,jdbcType=VARCHAR}, ",
        "#{domIndex,jdbcType=INTEGER}, #{attribute,jdbcType=VARCHAR}, ",
        "#{separator,jdbcType=VARCHAR}, #{pattern,jdbcType=VARCHAR}, ",
        "#{patternIndex,jdbcType=INTEGER}, #{itemType,jdbcType=INTEGER}, ",
        "#{itemIndex,jdbcType=INTEGER}, #{remark,jdbcType=VARCHAR}, ",
        "#{delFlag,jdbcType=INTEGER})"
    })
    @SelectKey(statement="CALL IDENTITY()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(ParserConfigItem record);

    @InsertProvider(type=ParserConfigItemSqlProvider.class, method="insertSelective")
    @SelectKey(statement="CALL IDENTITY()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(ParserConfigItem record);

    @SelectProvider(type=ParserConfigItemSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="PARSER_CONFIG_ID", property="parserConfigId", jdbcType=JdbcType.INTEGER),
        @Result(column="SUB_CONFIG_ID", property="subConfigId", jdbcType=JdbcType.INTEGER),
        @Result(column="COLUMN_TITLE", property="columnTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="SELECTOR", property="selector", jdbcType=JdbcType.VARCHAR),
        @Result(column="DOM_INDEX", property="domIndex", jdbcType=JdbcType.INTEGER),
        @Result(column="ATTRIBUTE", property="attribute", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEPARATOR", property="separator", jdbcType=JdbcType.VARCHAR),
        @Result(column="PATTERN", property="pattern", jdbcType=JdbcType.VARCHAR),
        @Result(column="PATTERN_INDEX", property="patternIndex", jdbcType=JdbcType.INTEGER),
        @Result(column="ITEM_TYPE", property="itemType", jdbcType=JdbcType.INTEGER),
        @Result(column="ITEM_INDEX", property="itemIndex", jdbcType=JdbcType.INTEGER),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEL_FLAG", property="delFlag", jdbcType=JdbcType.INTEGER)
    })
    List<ParserConfigItem> selectByExample(ParserConfigItemExample example);

    @Select({
        "select",
        "ID, PARSER_CONFIG_ID, SUB_CONFIG_ID, COLUMN_TITLE, SELECTOR, DOM_INDEX, ATTRIBUTE, ",
        "SEPARATOR, PATTERN, PATTERN_INDEX, ITEM_TYPE, ITEM_INDEX, REMARK, DEL_FLAG",
        "from PARSER_CONFIG_ITEM",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="PARSER_CONFIG_ID", property="parserConfigId", jdbcType=JdbcType.INTEGER),
        @Result(column="SUB_CONFIG_ID", property="subConfigId", jdbcType=JdbcType.INTEGER),
        @Result(column="COLUMN_TITLE", property="columnTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="SELECTOR", property="selector", jdbcType=JdbcType.VARCHAR),
        @Result(column="DOM_INDEX", property="domIndex", jdbcType=JdbcType.INTEGER),
        @Result(column="ATTRIBUTE", property="attribute", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEPARATOR", property="separator", jdbcType=JdbcType.VARCHAR),
        @Result(column="PATTERN", property="pattern", jdbcType=JdbcType.VARCHAR),
        @Result(column="PATTERN_INDEX", property="patternIndex", jdbcType=JdbcType.INTEGER),
        @Result(column="ITEM_TYPE", property="itemType", jdbcType=JdbcType.INTEGER),
        @Result(column="ITEM_INDEX", property="itemIndex", jdbcType=JdbcType.INTEGER),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEL_FLAG", property="delFlag", jdbcType=JdbcType.INTEGER)
    })
    ParserConfigItem selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ParserConfigItemSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ParserConfigItem record, @Param("example") ParserConfigItemExample example);

    @UpdateProvider(type=ParserConfigItemSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ParserConfigItem record, @Param("example") ParserConfigItemExample example);

    @UpdateProvider(type=ParserConfigItemSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ParserConfigItem record);

    @Update({
        "update PARSER_CONFIG_ITEM",
        "set PARSER_CONFIG_ID = #{parserConfigId,jdbcType=INTEGER},",
          "SUB_CONFIG_ID = #{subConfigId,jdbcType=INTEGER},",
          "COLUMN_TITLE = #{columnTitle,jdbcType=VARCHAR},",
          "SELECTOR = #{selector,jdbcType=VARCHAR},",
          "DOM_INDEX = #{domIndex,jdbcType=INTEGER},",
          "ATTRIBUTE = #{attribute,jdbcType=VARCHAR},",
          "SEPARATOR = #{separator,jdbcType=VARCHAR},",
          "PATTERN = #{pattern,jdbcType=VARCHAR},",
          "PATTERN_INDEX = #{patternIndex,jdbcType=INTEGER},",
          "ITEM_TYPE = #{itemType,jdbcType=INTEGER},",
          "ITEM_INDEX = #{itemIndex,jdbcType=INTEGER},",
          "REMARK = #{remark,jdbcType=VARCHAR},",
          "DEL_FLAG = #{delFlag,jdbcType=INTEGER}",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ParserConfigItem record);
}