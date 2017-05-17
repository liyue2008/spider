package com.github.liyue2008.spider.core.mapper;

import com.github.liyue2008.spider.core.entity.JobConfig;
import com.github.liyue2008.spider.core.entity.JobConfigExample;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
@Mapper
public interface JobConfigMapper {
    @SelectProvider(type=JobConfigSqlProvider.class, method="countByExample")
    int countByExample(JobConfigExample example);

    @DeleteProvider(type=JobConfigSqlProvider.class, method="deleteByExample")
    int deleteByExample(JobConfigExample example);

    @Delete({
        "delete from JOB_CONFIG",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into JOB_CONFIG (JOB_LABEL, PREVIEW_URL, ",
        "JOB_TYPE, PARSER_ID, ",
        "PARSER_CLASS, SORT_RESULT_INDEX, ",
        "REMARK, DEL_FLAG, ",
        "JOB_PARAMS)",
        "values (#{jobLabel,jdbcType=VARCHAR}, #{previewUrl,jdbcType=VARCHAR}, ",
        "#{jobType,jdbcType=VARCHAR}, #{parserId,jdbcType=INTEGER}, ",
        "#{parserClass,jdbcType=VARCHAR}, #{sortResultIndex,jdbcType=INTEGER}, ",
        "#{remark,jdbcType=VARCHAR}, #{delFlag,jdbcType=INTEGER}, ",
        "#{jobParams,jdbcType=CLOB})"
    })
    @SelectKey(statement="CALL IDENTITY()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(JobConfig record);

    @InsertProvider(type=JobConfigSqlProvider.class, method="insertSelective")
    @SelectKey(statement="CALL IDENTITY()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(JobConfig record);

    @SelectProvider(type=JobConfigSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="JOB_LABEL", property="jobLabel", jdbcType=JdbcType.VARCHAR),
        @Result(column="PREVIEW_URL", property="previewUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="JOB_TYPE", property="jobType", jdbcType=JdbcType.VARCHAR),
        @Result(column="PARSER_ID", property="parserId", jdbcType=JdbcType.INTEGER),
        @Result(column="PARSER_CLASS", property="parserClass", jdbcType=JdbcType.VARCHAR),
        @Result(column="SORT_RESULT_INDEX", property="sortResultIndex", jdbcType=JdbcType.INTEGER),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEL_FLAG", property="delFlag", jdbcType=JdbcType.INTEGER),
        @Result(column="JOB_PARAMS", property="jobParams", jdbcType=JdbcType.CLOB)
    })
    List<JobConfig> selectByExampleWithBLOBs(JobConfigExample example);

    @SelectProvider(type=JobConfigSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="JOB_LABEL", property="jobLabel", jdbcType=JdbcType.VARCHAR),
        @Result(column="PREVIEW_URL", property="previewUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="JOB_TYPE", property="jobType", jdbcType=JdbcType.VARCHAR),
        @Result(column="PARSER_ID", property="parserId", jdbcType=JdbcType.INTEGER),
        @Result(column="PARSER_CLASS", property="parserClass", jdbcType=JdbcType.VARCHAR),
        @Result(column="SORT_RESULT_INDEX", property="sortResultIndex", jdbcType=JdbcType.INTEGER),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEL_FLAG", property="delFlag", jdbcType=JdbcType.INTEGER)
    })
    List<JobConfig> selectByExample(JobConfigExample example);

    @Select({
        "select",
        "ID, JOB_LABEL, PREVIEW_URL, JOB_TYPE, PARSER_ID, PARSER_CLASS, SORT_RESULT_INDEX, ",
        "REMARK, DEL_FLAG, JOB_PARAMS",
        "from JOB_CONFIG",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="JOB_LABEL", property="jobLabel", jdbcType=JdbcType.VARCHAR),
        @Result(column="PREVIEW_URL", property="previewUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="JOB_TYPE", property="jobType", jdbcType=JdbcType.VARCHAR),
        @Result(column="PARSER_ID", property="parserId", jdbcType=JdbcType.INTEGER),
        @Result(column="PARSER_CLASS", property="parserClass", jdbcType=JdbcType.VARCHAR),
        @Result(column="SORT_RESULT_INDEX", property="sortResultIndex", jdbcType=JdbcType.INTEGER),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEL_FLAG", property="delFlag", jdbcType=JdbcType.INTEGER),
        @Result(column="JOB_PARAMS", property="jobParams", jdbcType=JdbcType.CLOB)
    })
    JobConfig selectByPrimaryKey(Integer id);

    @UpdateProvider(type=JobConfigSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") JobConfig record, @Param("example") JobConfigExample example);

    @UpdateProvider(type=JobConfigSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") JobConfig record, @Param("example") JobConfigExample example);

    @UpdateProvider(type=JobConfigSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") JobConfig record, @Param("example") JobConfigExample example);

    @UpdateProvider(type=JobConfigSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(JobConfig record);

    @Update({
        "update JOB_CONFIG",
        "set JOB_LABEL = #{jobLabel,jdbcType=VARCHAR},",
          "PREVIEW_URL = #{previewUrl,jdbcType=VARCHAR},",
          "JOB_TYPE = #{jobType,jdbcType=VARCHAR},",
          "PARSER_ID = #{parserId,jdbcType=INTEGER},",
          "PARSER_CLASS = #{parserClass,jdbcType=VARCHAR},",
          "SORT_RESULT_INDEX = #{sortResultIndex,jdbcType=INTEGER},",
          "REMARK = #{remark,jdbcType=VARCHAR},",
          "DEL_FLAG = #{delFlag,jdbcType=INTEGER},",
          "JOB_PARAMS = #{jobParams,jdbcType=CLOB}",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(JobConfig record);

    @Update({
        "update JOB_CONFIG",
        "set JOB_LABEL = #{jobLabel,jdbcType=VARCHAR},",
          "PREVIEW_URL = #{previewUrl,jdbcType=VARCHAR},",
          "JOB_TYPE = #{jobType,jdbcType=VARCHAR},",
          "PARSER_ID = #{parserId,jdbcType=INTEGER},",
          "PARSER_CLASS = #{parserClass,jdbcType=VARCHAR},",
          "SORT_RESULT_INDEX = #{sortResultIndex,jdbcType=INTEGER},",
          "REMARK = #{remark,jdbcType=VARCHAR},",
          "DEL_FLAG = #{delFlag,jdbcType=INTEGER}",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(JobConfig record);
}