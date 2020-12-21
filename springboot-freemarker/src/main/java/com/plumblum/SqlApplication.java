package com.plumblum;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.util.StopWatch;

/**
 * @author chenpeibin
 * @version 1.0
 * @date 2020/12/4 16:36
 */
@Slf4j
public class SqlApplication {
    public static void main(String[] args) throws Exception {
        SqlApplication sqlApplication = new SqlApplication();
        // String prepareSql = "SELECT\n" +
        //         "\tt1.workOrderNo AS '工单编码',\n" +
        //         "\tt1.createTime AS '创建时间',\n" +
        //         "t2.statusName AS '工单状态'\n" +
        //         "FROM\n" +
        //         "\tfsmworkorder t1\n" +
        //         "JOIN fsmworkorderstatus t2 ON t1.tenantId = t2.tenantId AND t1.statusCode = t2.statusCode\n" +
        //         "WHERE\n" +
        //         "\tt1.tenantId = #{tenantId}\n" +
        //         "<if test=\"statusCode != null and statusCode!=''\">\n" +
        //         "    AND t1.statusCode=#{statusCode}\n" +
        //         "</if>\n" +
        //         "<if test=\"workOrderNo!= null and workOrderNo!=''\">\n" +
        //         "    AND t1.workOrderNo=#{workOrderNo}\n" +
        //         "</if>";
        //
        // Map<String, Object> condition = new HashMap<>();
        // condition.put("tenantId",1);
        // condition.put("statusCode","45000");
        // condition.put("start", 1);
        // condition.put("limit", 10);

        String prepareSql = "        select\n" +
                "        gmv,\n" +
                "        cmv,\n" +
                "        pmv\n" +
                "        from dm_sl_store_data_daily\n" +
                "        <where>\n" +
                "            <if test=\"id != null\">\n" +
                "                and id = #{id}\n" +
                "            </if>\n" +
                "            <if test=\"idList != null and idList.size > 0\">\n" +
                "                and id in\n" +
                "                <foreach collection=\"idList\" item=\"id\" index=\"i\" open=\"(\" separator=\",\" close=\")\">\n" +
                "                    #{id}\n" +
                "                </foreach>\n" +
                "            </if>\n" +
                "        </where>";
        Map<String, Object> condition = new HashMap<>();
        ArrayList<String> list = new ArrayList<>();
        list.add("1 ");
        list.add("2");
        condition.put("idList", list);

        StopWatch stopWatch = new StopWatch();
        int size = 1;

        SqlSessionFactory sqlSessionFactory = sqlSessionFactory();
        stopWatch.start("mybatis");
        for (int i = 0; i < size; i++) {
            String s = sqlApplication.toSql(sqlSessionFactory,prepareSql, condition);
            System.out.println(s);
        }
        stopWatch.stop();
        stopWatch.prettyPrint();
        stopWatch.start("common");
        for (int i = 0; i < size; i++) {
            // System.out.println(" ");
            // System.out.println(" ");
        }
        stopWatch.stop();
        stopWatch.prettyPrint();
        log.info("{}", stopWatch.prettyPrint());
    }

    private String toSql(SqlSessionFactory sqlSessionFactory ,String prepareSql, Map<String, Object> condition) throws Exception {
        XMLLanguageDriver driver = new XMLLanguageDriver();
        String script = "<script>" + prepareSql + "</script>";
        SqlSource sqlSource;
        BoundSql boundSql;

        try {

            sqlSource = driver.createSqlSource(sqlSessionFactory.getConfiguration(), script,Map.class);
            boundSql = sqlSource.getBoundSql(condition);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("");
        }
        Configuration configuration = sqlSessionFactory.getConfiguration();
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");

        if (parameterMappings.size() == 0 || parameterObject == null) {
            return sql;
        }

        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
            sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
        } else {
            MetaObject metaObject = configuration.newMetaObject(parameterObject);
            for (ParameterMapping parameterMapping : parameterMappings) {
                String propertyName = parameterMapping.getProperty();
                if (metaObject.hasGetter(propertyName)) {
                    Object obj = metaObject.getValue(propertyName);
                    sql = sql.replaceFirst("\\?", getParameterValue(obj));
                } else if (boundSql.hasAdditionalParameter(propertyName)) {
                    Object obj = boundSql.getAdditionalParameter(propertyName);
                    sql = sql.replaceFirst("\\?", getParameterValue(obj));
                }
            }
        }
        return sql;
    }

    private static SqlSessionFactory sqlSessionFactory() {
        Configuration configuration = new Configuration();
        //开启驼峰规则
        configuration.setMapUnderscoreToCamelCase(true);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        return sqlSessionFactory;
    }


    private static String getParameterValue(Object obj) {
        String value;
        if (obj instanceof String) {
            value = "'" + StringEscapeUtils.escapeSql(obj.toString()) + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(obj) + "'";
        } else if (obj instanceof String[]) {
            String str = "";
            for (String s : (String[]) obj) {
                str += "'" + StringEscapeUtils.escapeSql(s.trim()) + "',";
            }
            value = str.substring(0, str.length() - 1);
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }
        }
        return value;
    }
}
