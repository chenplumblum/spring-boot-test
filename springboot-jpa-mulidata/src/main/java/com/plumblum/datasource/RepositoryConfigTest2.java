package com.plumblum.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
        entityManagerFactoryRef="entityManagerFactoryTest2",
        transactionManagerRef="transactionManagerTest2",
        basePackages= { "com.plumblum.repository.Test2" })
public class RepositoryConfigTest2 {
    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("test2DS")
    private DataSource test2DS;

    @Bean(name = "entityManagerTest2")
//    @Primary
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryTest2(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactoryTest2")
//    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryTest2 (EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(test2DS)
                .properties(getVendorProperties())
                .packages("com.plumblum.entity") //设置实体类所在位置
                .persistenceUnit("test2PersistenceUnit")
                .build();
    }

    private Map<String, Object> getVendorProperties() {
        return jpaProperties.getHibernateProperties(new HibernateSettings());
    }

    @Bean(name = "transactionManagerTest2")
//    @Primary
    PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryTest2(builder).getObject());
    }

}
