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
 * @description form数据源的jpa配置
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryForm",
        transactionManagerRef = "transactionManagerForm",
        basePackages = { "com.wu.ordersystem.repository.form" } // repository包所在位置
)
public class FormConfigDatasource {
    @Autowired
    @Qualifier("formDataSource")
    private DataSource formDataSource;

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private HibernateProperties hibernateProperties;

    private Map<String, Object> getVendorProperties() {
        return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
    }

    @Bean(name = "entityManagerForm")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryForm(builder)
                .getObject()
                .createEntityManager();
    }

    @Bean(name = "entityManagerFactoryForm")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryForm(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(formDataSource)
                .packages("com.wu.ordersystem.pojo.domain.form") // 实体类所在位置
                .persistenceUnit("formPersistenceUnit")
                .properties(getVendorProperties())
                .build();
    }

    @Bean(name = "transactionManagerForm")
    public PlatformTransactionManager transactionManagerForm(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryForm(builder).getObject());
    }
}
