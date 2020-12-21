package com.plumblum;

/**
 * @author chenpeibin
 * @version 1.0
 * @date 2020/12/4 16:26
 */
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.jdbc.SqlRunner;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.type.TypeHandlerRegistry;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaSqlApplication {

    // 直接注入
    // @Autowired
    // private SqlSessionFactory sqlSessionFactory;

    public static void main(String[] args) {
        JavaSqlApplication test = new JavaSqlApplication();

        /*
         SELECT
         t1.workOrderNo AS '工单编码',
         t1.createTime AS '创建时间',
         t2.statusName AS '工单状态'
         FROM
         fsmworkorder t1
         JOIN fsmworkorderstatus t2 ON t1.tenantId = t2.tenantId AND t1.statusCode = t2.statusCode
         WHERE
         t1.tenantId = #{tenantId}
         <if test="statusCode != null and statusCode!=''">
         AND t1.statusCode=#{statusCode}
         </if>
         <if test="workOrderNo!= null and workOrderNo!=''">
         AND t1.workOrderNo=#{workOrderNo}
         </if>
         */
        String prepareSql = "SELECT\n" +
                "\tt1.workOrderNo AS '工单编码',\n" +
                "\tt1.createTime AS '创建时间',\n" +
                "t2.statusName AS '工单状态'\n" +
                "FROM\n" +
                "\tfsmworkorder t1\n" +
                "JOIN fsmworkorderstatus t2 ON t1.tenantId = t2.tenantId AND t1.statusCode = t2.statusCode\n" +
                "WHERE\n" +
                "\tt1.tenantId = #{tenantId}\n" +
                "<if test=\"statusCode != null and statusCode!=''\">\n" +
                "    AND t1.statusCode=#{statusCode}\n" +
                "</if>\n" +
                "<if test=\"workOrderNo!= null and workOrderNo!=''\">\n" +
                "    AND t1.workOrderNo=#{workOrderNo}\n" +
                "</if>";

        Map<String, Object> condition = new HashMap<>();
        condition.put("tenantId",1);
        condition.put("statusCode","45000");
        condition.put("start", 1);
        condition.put("limit", 10);

        try {
            test.execSql(prepareSql, condition);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 配置数据库信息，获取SqlSessionFactory
     * @return
     */
    private SqlSessionFactory createSqlSessionFactory() {

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test?useUnicode=yes&characterEncoding=UTF-8";
        String username = "root";
        String password = "root";
        //创建连接池
        DataSource dataSource = new PooledDataSource(driver, url, username, password);
        //事务
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        //创建环境
        Environment environment = new Environment("development", transactionFactory, dataSource);
        //创建配置
        Configuration configuration = new Configuration(environment);
        //开启驼峰规则
        configuration.setMapUnderscoreToCamelCase(true);
        //加入资源（Mapper接口）
        // configuration.addMapper(UserMapper.class);
        //
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        return sqlSessionFactory;
    }

    public Map<String, Object> execSql(String prepareSql, Map<String, Object> condition) throws Exception {

        Map<String, Object> page = new HashMap<>();
        SqlSession sqlSession = null;


        try {
            SqlSessionFactory sqlSessionFactory = this.createSqlSessionFactory();
            sqlSession = sqlSessionFactory.openSession();
            Connection connection = sqlSession.getConnection();
            SqlRunner sqlRunner = new SqlRunner(connection);
            String realSql = this.toSql(prepareSql, condition);

            // 获取查询数量
            Integer sqlCount = this.searchSqlCount(prepareSql, condition);
            page.put("count", sqlCount);

            if (sqlCount < 1) {
                page.put("results", Collections.EMPTY_LIST);
                return page;
            }

            // 当查询语句中添加了LIMIT 时，使用sql语句中自带的
            if (!realSql.contains("limit")
                    && !realSql.contains("limit".toUpperCase())
                    && condition.containsKey("limit")
                    && condition.containsKey("start")) {
                realSql += " LIMIT " + condition.get("start") + "," + condition.get("limit");
            }

            // TODO:执行后的SQL语句
            /*
            SELECT t1.workOrderNo AS '工单编码', t1.createTime AS '创建时间', t2.statusName AS '工单状态'
            FROM fsmworkorder t1
            JOIN fsmworkorderstatus t2 ON t1.tenantId = t2.tenantId AND t1.statusCode = t2.statusCode
            WHERE t1.tenantId = 1 AND t1.statusCode='45000'
            LIMIT 1,10
             */
            System.out.println("===SQL 语句===：");
            System.out.println(realSql);

            // TODO:正式执行时放开
            // page.put("results", sqlRunner.selectAll(realSql));
            return page;
            //return sqlRunner.selectAll(realSql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("");
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    public Integer searchSqlCount(String prepareSql, Map<String, Object> condition) throws Exception {

        SqlSessionFactory sqlSessionFactory = this.createSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Connection connection = sqlSession.getConnection();
        SqlRunner sqlRunner = new SqlRunner(connection);
        String sql = toSql(prepareSql, condition);

        if (StringUtils.isEmpty(sql)) {
            return 0;
        }

        // 将sql中前面的select 和 from 之间的信息替换成count(1)，用于查询数量
        int start = 0, end = 0;
        Pattern patternSelect = Pattern.compile("SELECT", Pattern.CASE_INSENSITIVE);
        Matcher matcherSelect = patternSelect.matcher(sql);

        while (matcherSelect.find()) {
            start = matcherSelect.end();
            break;
        }

        // 获取from的位置
        Pattern patternFrom = Pattern.compile("FROM" , Pattern.CASE_INSENSITIVE);
        Matcher matcherFrom = patternFrom.matcher(sql);

        while (matcherFrom.find()) {
            end = matcherFrom.start();
            break;
        }

        String sqlCount = StringUtils.replaceOnce(sql, sql.substring(start, end), " COUNT(1) ");

        // TODO:查询数量语句
        /*
        SELECT COUNT(1)
        FROM fsmworkorder t1
        JOIN fsmworkorderstatus t2 ON t1.tenantId = t2.tenantId AND t1.statusCode = t2.statusCode
        WHERE t1.tenantId = 1 AND t1.statusCode='45000'
         */
        System.out.println("===查询条数 SQL===：");
        System.out.println(sqlCount);

        return 0;

        // TODO:正式执行时放开
        /*
        try {
            return Integer.parseInt(String.valueOf(sqlRunner.selectAll(sqlCount).get(0).get("COUNT(1)")));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("");
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        */
    }

    private String toSql(String prepareSql, Map<String, Object> condition) throws Exception {

        XMLLanguageDriver driver = new XMLLanguageDriver();
        String script = "<script>" + prepareSql + "</script>";
        SqlSource sqlSource;
        BoundSql boundSql;
        SqlSessionFactory sqlSessionFactory = this.createSqlSessionFactory();
        try {
            sqlSource = driver.createSqlSource(sqlSessionFactory.getConfiguration(), script, condition.getClass());
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

    private static String getParameterValue(Object obj) {
        String value;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(obj) + "'";
        } else if (obj instanceof String[]){
            String str = "";
            for(String s :(String[]) obj){
                str += "'" + s.trim() + "',";
            }
            value = str.substring(0,str.length()-1);
        }else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }
        }
        return value;
    }
}

