package com.github.liyue2008.spider.core.mapper;

import com.github.liyue2008.spider.core.entity.ParserConfigItem;
import com.github.liyue2008.spider.core.entity.ParserConfigItemExample;

import java.util.List;
import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class ParserConfigItemSqlProvider {

    public String countByExample(ParserConfigItemExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("PARSER_CONFIG_ITEM");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(ParserConfigItemExample example) {
        BEGIN();
        DELETE_FROM("PARSER_CONFIG_ITEM");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(ParserConfigItem record) {
        BEGIN();
        INSERT_INTO("PARSER_CONFIG_ITEM");
        
        if (record.getParserConfigId() != null) {
            VALUES("PARSER_CONFIG_ID", "#{parserConfigId,jdbcType=INTEGER}");
        }
        
        if (record.getSubConfigId() != null) {
            VALUES("SUB_CONFIG_ID", "#{subConfigId,jdbcType=INTEGER}");
        }
        
        if (record.getColumnTitle() != null) {
            VALUES("COLUMN_TITLE", "#{columnTitle,jdbcType=VARCHAR}");
        }
        
        if (record.getSelector() != null) {
            VALUES("SELECTOR", "#{selector,jdbcType=VARCHAR}");
        }
        
        if (record.getDomIndex() != null) {
            VALUES("DOM_INDEX", "#{domIndex,jdbcType=INTEGER}");
        }
        
        if (record.getAttribute() != null) {
            VALUES("ATTRIBUTE", "#{attribute,jdbcType=VARCHAR}");
        }
        
        if (record.getSeparator() != null) {
            VALUES("SEPARATOR", "#{separator,jdbcType=VARCHAR}");
        }
        
        if (record.getPattern() != null) {
            VALUES("PATTERN", "#{pattern,jdbcType=VARCHAR}");
        }
        
        if (record.getPatternIndex() != null) {
            VALUES("PATTERN_INDEX", "#{patternIndex,jdbcType=INTEGER}");
        }
        
        if (record.getItemType() != null) {
            VALUES("ITEM_TYPE", "#{itemType,jdbcType=INTEGER}");
        }
        
        if (record.getItemIndex() != null) {
            VALUES("ITEM_INDEX", "#{itemIndex,jdbcType=INTEGER}");
        }
        
        if (record.getRemark() != null) {
            VALUES("REMARK", "#{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getDelFlag() != null) {
            VALUES("DEL_FLAG", "#{delFlag,jdbcType=INTEGER}");
        }
        
        return SQL();
    }

    public String selectByExample(ParserConfigItemExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("ID");
        } else {
            SELECT("ID");
        }
        SELECT("PARSER_CONFIG_ID");
        SELECT("SUB_CONFIG_ID");
        SELECT("COLUMN_TITLE");
        SELECT("SELECTOR");
        SELECT("DOM_INDEX");
        SELECT("ATTRIBUTE");
        SELECT("SEPARATOR");
        SELECT("PATTERN");
        SELECT("PATTERN_INDEX");
        SELECT("ITEM_TYPE");
        SELECT("ITEM_INDEX");
        SELECT("REMARK");
        SELECT("DEL_FLAG");
        FROM("PARSER_CONFIG_ITEM");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        ParserConfigItem record = (ParserConfigItem) parameter.get("record");
        ParserConfigItemExample example = (ParserConfigItemExample) parameter.get("example");
        
        BEGIN();
        UPDATE("PARSER_CONFIG_ITEM");
        
        if (record.getId() != null) {
            SET("ID = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getParserConfigId() != null) {
            SET("PARSER_CONFIG_ID = #{record.parserConfigId,jdbcType=INTEGER}");
        }
        
        if (record.getSubConfigId() != null) {
            SET("SUB_CONFIG_ID = #{record.subConfigId,jdbcType=INTEGER}");
        }
        
        if (record.getColumnTitle() != null) {
            SET("COLUMN_TITLE = #{record.columnTitle,jdbcType=VARCHAR}");
        }
        
        if (record.getSelector() != null) {
            SET("SELECTOR = #{record.selector,jdbcType=VARCHAR}");
        }
        
        if (record.getDomIndex() != null) {
            SET("DOM_INDEX = #{record.domIndex,jdbcType=INTEGER}");
        }
        
        if (record.getAttribute() != null) {
            SET("ATTRIBUTE = #{record.attribute,jdbcType=VARCHAR}");
        }
        
        if (record.getSeparator() != null) {
            SET("SEPARATOR = #{record.separator,jdbcType=VARCHAR}");
        }
        
        if (record.getPattern() != null) {
            SET("PATTERN = #{record.pattern,jdbcType=VARCHAR}");
        }
        
        if (record.getPatternIndex() != null) {
            SET("PATTERN_INDEX = #{record.patternIndex,jdbcType=INTEGER}");
        }
        
        if (record.getItemType() != null) {
            SET("ITEM_TYPE = #{record.itemType,jdbcType=INTEGER}");
        }
        
        if (record.getItemIndex() != null) {
            SET("ITEM_INDEX = #{record.itemIndex,jdbcType=INTEGER}");
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
        UPDATE("PARSER_CONFIG_ITEM");
        
        SET("ID = #{record.id,jdbcType=INTEGER}");
        SET("PARSER_CONFIG_ID = #{record.parserConfigId,jdbcType=INTEGER}");
        SET("SUB_CONFIG_ID = #{record.subConfigId,jdbcType=INTEGER}");
        SET("COLUMN_TITLE = #{record.columnTitle,jdbcType=VARCHAR}");
        SET("SELECTOR = #{record.selector,jdbcType=VARCHAR}");
        SET("DOM_INDEX = #{record.domIndex,jdbcType=INTEGER}");
        SET("ATTRIBUTE = #{record.attribute,jdbcType=VARCHAR}");
        SET("SEPARATOR = #{record.separator,jdbcType=VARCHAR}");
        SET("PATTERN = #{record.pattern,jdbcType=VARCHAR}");
        SET("PATTERN_INDEX = #{record.patternIndex,jdbcType=INTEGER}");
        SET("ITEM_TYPE = #{record.itemType,jdbcType=INTEGER}");
        SET("ITEM_INDEX = #{record.itemIndex,jdbcType=INTEGER}");
        SET("REMARK = #{record.remark,jdbcType=VARCHAR}");
        SET("DEL_FLAG = #{record.delFlag,jdbcType=INTEGER}");
        
        ParserConfigItemExample example = (ParserConfigItemExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(ParserConfigItem record) {
        BEGIN();
        UPDATE("PARSER_CONFIG_ITEM");
        
        if (record.getParserConfigId() != null) {
            SET("PARSER_CONFIG_ID = #{parserConfigId,jdbcType=INTEGER}");
        }
        
        if (record.getSubConfigId() != null) {
            SET("SUB_CONFIG_ID = #{subConfigId,jdbcType=INTEGER}");
        }
        
        if (record.getColumnTitle() != null) {
            SET("COLUMN_TITLE = #{columnTitle,jdbcType=VARCHAR}");
        }
        
        if (record.getSelector() != null) {
            SET("SELECTOR = #{selector,jdbcType=VARCHAR}");
        }
        
        if (record.getDomIndex() != null) {
            SET("DOM_INDEX = #{domIndex,jdbcType=INTEGER}");
        }
        
        if (record.getAttribute() != null) {
            SET("ATTRIBUTE = #{attribute,jdbcType=VARCHAR}");
        }
        
        if (record.getSeparator() != null) {
            SET("SEPARATOR = #{separator,jdbcType=VARCHAR}");
        }
        
        if (record.getPattern() != null) {
            SET("PATTERN = #{pattern,jdbcType=VARCHAR}");
        }
        
        if (record.getPatternIndex() != null) {
            SET("PATTERN_INDEX = #{patternIndex,jdbcType=INTEGER}");
        }
        
        if (record.getItemType() != null) {
            SET("ITEM_TYPE = #{itemType,jdbcType=INTEGER}");
        }
        
        if (record.getItemIndex() != null) {
            SET("ITEM_INDEX = #{itemIndex,jdbcType=INTEGER}");
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

    protected void applyWhere(ParserConfigItemExample example, boolean includeExamplePhrase) {
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
        List<ParserConfigItemExample.Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            ParserConfigItemExample.Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<ParserConfigItemExample.Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    ParserConfigItemExample.Criterion criterion = criterions.get(j);
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