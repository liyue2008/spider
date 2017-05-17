package com.github.liyue2008.spider.core.mapper;

import com.github.liyue2008.spider.core.entity.ParserConfig;
import com.github.liyue2008.spider.core.entity.ParserConfigExample;
import com.github.liyue2008.spider.core.entity.ParserConfigExample.Criteria;
import com.github.liyue2008.spider.core.entity.ParserConfigExample.Criterion;

import java.util.List;
import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class ParserConfigSqlProvider {

    public String countByExample(ParserConfigExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("PARSER_CONFIG");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(ParserConfigExample example) {
        BEGIN();
        DELETE_FROM("PARSER_CONFIG");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(ParserConfig record) {
        BEGIN();
        INSERT_INTO("PARSER_CONFIG");
        
        if (record.getName() != null) {
            VALUES("NAME", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            VALUES("TYPE", "#{type,jdbcType=INTEGER}");
        }
        
        if (record.getListItemSelector() != null) {
            VALUES("LIST_ITEM_SELECTOR", "#{listItemSelector,jdbcType=VARCHAR}");
        }
        
        if (record.getPreviewUrl() != null) {
            VALUES("PREVIEW_URL", "#{previewUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getUserAgent() != null) {
            VALUES("USER_AGENT", "#{userAgent,jdbcType=VARCHAR}");
        }
        
        if (record.getReferer() != null) {
            VALUES("REFERER", "#{referer,jdbcType=VARCHAR}");
        }
        
        if (record.getTimeout() != null) {
            VALUES("TIMEOUT", "#{timeout,jdbcType=INTEGER}");
        }
        
        if (record.getRemark() != null) {
            VALUES("REMARK", "#{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getDelFlag() != null) {
            VALUES("DEL_FLAG", "#{delFlag,jdbcType=INTEGER}");
        }
        
        return SQL();
    }

    public String selectByExample(ParserConfigExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("ID");
        } else {
            SELECT("ID");
        }
        SELECT("NAME");
        SELECT("TYPE");
        SELECT("LIST_ITEM_SELECTOR");
        SELECT("PREVIEW_URL");
        SELECT("USER_AGENT");
        SELECT("REFERER");
        SELECT("TIMEOUT");
        SELECT("REMARK");
        SELECT("DEL_FLAG");
        FROM("PARSER_CONFIG");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        ParserConfig record = (ParserConfig) parameter.get("record");
        ParserConfigExample example = (ParserConfigExample) parameter.get("example");
        
        BEGIN();
        UPDATE("PARSER_CONFIG");
        
        if (record.getId() != null) {
            SET("ID = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            SET("NAME = #{record.name,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            SET("TYPE = #{record.type,jdbcType=INTEGER}");
        }
        
        if (record.getListItemSelector() != null) {
            SET("LIST_ITEM_SELECTOR = #{record.listItemSelector,jdbcType=VARCHAR}");
        }
        
        if (record.getPreviewUrl() != null) {
            SET("PREVIEW_URL = #{record.previewUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getUserAgent() != null) {
            SET("USER_AGENT = #{record.userAgent,jdbcType=VARCHAR}");
        }
        
        if (record.getReferer() != null) {
            SET("REFERER = #{record.referer,jdbcType=VARCHAR}");
        }
        
        if (record.getTimeout() != null) {
            SET("TIMEOUT = #{record.timeout,jdbcType=INTEGER}");
        }
        
        if (record.getRemark() != null) {
            SET("REMARK = #{record.remark,jdbcType=VARCHAR}");
        }
        
        if (record.getDelFlag() != null) {
            SET("DEL_FLAG = #{record.delFlag,jdbcType=INTEGER}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("PARSER_CONFIG");
        
        SET("ID = #{record.id,jdbcType=INTEGER}");
        SET("NAME = #{record.name,jdbcType=VARCHAR}");
        SET("TYPE = #{record.type,jdbcType=INTEGER}");
        SET("LIST_ITEM_SELECTOR = #{record.listItemSelector,jdbcType=VARCHAR}");
        SET("PREVIEW_URL = #{record.previewUrl,jdbcType=VARCHAR}");
        SET("USER_AGENT = #{record.userAgent,jdbcType=VARCHAR}");
        SET("REFERER = #{record.referer,jdbcType=VARCHAR}");
        SET("TIMEOUT = #{record.timeout,jdbcType=INTEGER}");
        SET("REMARK = #{record.remark,jdbcType=VARCHAR}");
        SET("DEL_FLAG = #{record.delFlag,jdbcType=INTEGER}");
        
        ParserConfigExample example = (ParserConfigExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(ParserConfig record) {
        BEGIN();
        UPDATE("PARSER_CONFIG");
        
        if (record.getName() != null) {
            SET("NAME = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            SET("TYPE = #{type,jdbcType=INTEGER}");
        }
        
        if (record.getListItemSelector() != null) {
            SET("LIST_ITEM_SELECTOR = #{listItemSelector,jdbcType=VARCHAR}");
        }
        
        if (record.getPreviewUrl() != null) {
            SET("PREVIEW_URL = #{previewUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getUserAgent() != null) {
            SET("USER_AGENT = #{userAgent,jdbcType=VARCHAR}");
        }
        
        if (record.getReferer() != null) {
            SET("REFERER = #{referer,jdbcType=VARCHAR}");
        }
        
        if (record.getTimeout() != null) {
            SET("TIMEOUT = #{timeout,jdbcType=INTEGER}");
        }
        
        if (record.getRemark() != null) {
            SET("REMARK = #{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getDelFlag() != null) {
            SET("DEL_FLAG = #{delFlag,jdbcType=INTEGER}");
        }
        
        WHERE("ID = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }

    protected void applyWhere(ParserConfigExample example, boolean includeExamplePhrase) {
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
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
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