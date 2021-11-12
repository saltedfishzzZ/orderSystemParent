package com.wu.ordersystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
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
 * @author saltedfishzzZ
 * @date 2021-11-12
 * @description resource数据源的jpa配置
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryResource",
        transactionManagerRef = "transactionManagerResource",
        basePackages = { "com.wu.ordersystem.repository.resource" } // repository包所在位置
)
public class ResourceConfigDatasource {
    @Autowired
    @Qualifier("resourceDataSource")
    private DataSource resourceDataSource;

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private HibernateProperties hibernateProperties;

    private Map<String, Object> getVendorProperties() {
        return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
    }

    @Primary
    @Bean(name = "entityManagerResource")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryResource(builder)
                .getObject()
                .createEntityManager();
    }

    @Primary
    @Bean(name = "entityManagerFactoryResource")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryResource(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(resourceDataSource)
                .packages("com.wu.ordersystem.pojo.domain.resource") // 实体类所在位置
                .persistenceUnit("resourcePersistenceUnit")
                .properties(getVendorProperties())
                .build();
    }

    @Primary
    @Bean(name = "transactionManagerResource")
    public PlatformTransactionManager transactionManagerResource(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryResource(builder).getObject());
    }
}
