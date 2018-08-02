package com.plumblum.datasource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @Auther: cpb
 * @Date: 2018/8/2 14:13
 * @Description:
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactoryTest1",
        transactionManagerRef="transactionManagerTest1",
        basePackages= { "com.plumblum.repository.Test1" })
public class RepositoryConfigTest1 {
    /**
     *
     */
    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("test1DS")
    private DataSource test1DS;

    @Bean(name = "entityManagerTest1")
    @Primary
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryTest1(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactoryTest1")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryTest1 (EntityManagerFactoryBuilder builder) {
        return builder
                //设置数据源
                .dataSource(test1DS)
                //设置数据源属性
                .properties(getVendorProperties())
                //设置实体类所在位置.扫描所有带有 @Entity 注解的类
                .packages("com.plumblum.entity")
                // Spring会将EntityManagerFactory注入到Repository之中.有了 EntityManagerFactory之后,
                // Repository就能用它来创建 EntityManager 了,然后Entity就可以针对数据库执行操作
                .persistenceUnit("test1PersistenceUnit")
                .build();

    }


    private Map<String, Object> getVendorProperties() {
        return jpaProperties.getHibernateProperties(new HibernateSettings());
    }

    @Bean(name = "transactionManagerTest1")
    @Primary
    PlatformTransactionManager transactionManagerTest1(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryTest1(builder).getObject());
    }

}
