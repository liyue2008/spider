package com.github.liyue2008.spider.core.mapper;

import com.github.liyue2008.spider.core.entity.JobConfig;
import com.github.liyue2008.spider.core.entity.JobConfigExample;

import java.util.List;
import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class JobConfigSqlProvider {

    public String countByExample(JobConfigExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("JOB_CONFIG");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(JobConfigExample example) {
        BEGIN();
        DELETE_FROM("JOB_CONFIG");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(JobConfig record) {
        BEGIN();
        INSERT_INTO("JOB_CONFIG");
        
        if (record.getJobLabel() != null) {
            VALUES("JOB_LABEL", "#{jobLabel,jdbcType=VARCHAR}");
        }
        
        if (record.getPreviewUrl() != null) {
            VALUES("PREVIEW_URL", "#{previewUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getJobType() != null) {
            VALUES("JOB_TYPE", "#{jobType,jdbcType=VARCHAR}");
        }
        
        if (record.getParserId() != null) {
            VALUES("PARSER_ID", "#{parserId,jdbcType=INTEGER}");
        }
        
        if (record.getParserClass() != null) {
            VALUES("PARSER_CLASS", "#{parserClass,jdbcType=VARCHAR}");
        }
        
        if (record.getSortResultIndex() != null) {
            VALUES("SORT_RESULT_INDEX", "#{sortResultIndex,jdbcType=INTEGER}");
        }
        
        if (record.getRemark() != null) {
            VALUES("REMARK", "#{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getDelFlag() != null) {
            VALUES("DEL_FLAG", "#{delFlag,jdbcType=INTEGER}");
        }
        
        if (record.getJobParams() != null) {
            VALUES("JOB_PARAMS", "#{jobParams,jdbcType=CLOB}");
        }
        
        return SQL();
    }

    public String selectByExampleWithBLOBs(JobConfigExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("ID");
        } else {
            SELECT("ID");
        }
        SELECT("JOB_LABEL");
        SELECT("PREVIEW_URL");
        SELECT("JOB_TYPE");
        SELECT("PARSER_ID");
        SELECT("PARSER_CLASS");
        SELECT("SORT_RESULT_INDEX");
        SELECT("REMARK");
        SELECT("DEL_FLAG");
        SELECT("JOB_PARAMS");
        FROM("JOB_CONFIG");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String selectByExample(JobConfigExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("ID");
        } else {
            SELECT("ID");
        }
        SELECT("JOB_LABEL");
        SELECT("PREVIEW_URL");
        SELECT("JOB_TYPE");
        SELECT("PARSER_ID");
        SELECT("PARSER_CLASS");
        SELECT("SORT_RESULT_INDEX");
        SELECT("REMARK");
        SELECT("DEL_FLAG");
        FROM("JOB_CONFIG");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        JobConfig record = (JobConfig) parameter.get("record");
        JobConfigExample example = (JobConfigExample) parameter.get("example");
        
        BEGIN();
        UPDATE("JOB_CONFIG");
        
        if (record.getId() != null) {
            SET("ID = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getJobLabel() != null) {
            SET("JOB_LABEL = #{record.jobLabel,jdbcType=VARCHAR}");
        }
        
        if (record.getPreviewUrl() != null) {
            SET("PREVIEW_URL = #{record.previewUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getJobType() != null) {
            SET("JOB_TYPE = #{record.jobType,jdbcType=VARCHAR}");
        }
        
        if (record.getParserId() != null) {
            SET("PARSER_ID = #{record.parserId,jdbcType=INTEGER}");
        }
        
        if (record.getParserClass() != null) {
            SET("PARSER_CLASS = #{record.parserClass,jdbcType=VARCHAR}");
        }
        
        if (record.getSortResultIndex() != null) {
            SET("SORT_RESULT_INDEX = #{record.sortResultIndex,jdbcType=INTEGER}");
        }
        
        if (record.getRemark() != null) {
            SET("REMARK = #{record.remark,jdbcType=VARCHAR}");
        }
        
        if (record.getDelFlag() != null) {
            SET("DEL_FLAG = #{record.delFlag,jdbcType=INTEGER}");
        }
        
        if (record.getJobParams() != null) {
            SET("JOB_PARAMS = #{record.jobParams,jdbcType=CLOB}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExampleWithBLOBs(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("JOB_CONFIG");
        
        SET("ID = #{record.id,jdbcType=INTEGER}");
        SET("JOB_LABEL = #{record.jobLabel,jdbcType=VARCHAR}");
        SET("PREVIEW_URL = #{record.previewUrl,jdbcType=VARCHAR}");
        SET("JOB_TYPE = #{record.jobType,jdbcType=VARCHAR}");
        SET("PARSER_ID = #{record.parserId,jdbcType=INTEGER}");
        SET("PARSER_CLASS = #{record.parserClass,jdbcType=VARCHAR}");
        SET("SORT_RESULT_INDEX = #{record.sortResultIndex,jdbcType=INTEGER}");
        SET("REMARK = #{record.remark,jdbcType=VARCHAR}");
        SET("DEL_FLAG = #{record.delFlag,jdbcType=INTEGER}");
        SET("JOB_PARAMS = #{record.jobParams,jdbcType=CLOB}");
        
        JobConfigExample example = (JobConfigExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("JOB_CONFIG");
        
        SET("ID = #{record.id,jdbcType=INTEGER}");
        SET("JOB_LABEL = #{record.jobLabel,jdbcType=VARCHAR}");
        SET("PREVIEW_URL = #{record.previewUrl,jdbcType=VARCHAR}");
        SET("JOB_TYPE = #{record.jobType,jdbcType=VARCHAR}");
        SET("PARSER_ID = #{record.parserId,jdbcType=INTEGER}");
        SET("PARSER_CLASS = #{record.parserClass,jdbcType=VARCHAR}");
        SET("SORT_RESULT_INDEX = #{record.sortResultIndex,jdbcType=INTEGER}");
        SET("REMARK = #{record.remark,jdbcType=VARCHAR}");
        SET("DEL_FLAG = #{record.delFlag,jdbcType=INTEGER}");
        
        JobConfigExample example = (JobConfigExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(JobConfig record) {
        BEGIN();
        UPDATE("JOB_CONFIG");
        
        if (record.getJobLabel() != null) {
            SET("JOB_LABEL = #{jobLabel,jdbcType=VARCHAR}");
        }
        
        if (record.getPreviewUrl() != null) {
            SET("PREVIEW_URL = #{previewUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getJobType() != null) {
            SET("JOB_TYPE = #{jobType,jdbcType=VARCHAR}");
        }
        
        if (record.getParserId() != null) {
            SET("PARSER_ID = #{parserId,jdbcType=INTEGER}");
        }
        
        if (record.getParserClass() != null) {
            SET("PARSER_CLASS = #{parserClass,jdbcType=VARCHAR}");
        }
        
        if (record.getSortResultIndex() != null) {
            SET("SORT_RESULT_INDEX = #{sortResultIndex,jdbcType=INTEGER}");
        }
        
        if (record.getRemark() != null) {
            SET("REMARK = #{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getDelFlag() != null) {
            SET("DEL_FLAG = #{delFlag,jdbcType=INTEGER}");
        }
        
        if (record.getJobParams() != null) {
            SET("JOB_PARAMS = #{jobParams,jdbcType=CLOB}");
        }
        
        WHERE("ID = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }

    protected void applyWhere(JobConfigExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<JobConfigExample.Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            JobConfigExample.Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<JobConfigExample.Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    JobConfigExample.Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            WHERE(sb.toString());
        }
    }
}