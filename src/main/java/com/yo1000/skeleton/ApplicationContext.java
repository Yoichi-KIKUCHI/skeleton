package com.yo1000.skeleton;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

/**
 * Created by yoichi.kikuchi on 2015/07/04.
 */
@SpringBootApplication
public class ApplicationContext {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationContext.class, args);
    }

    @Autowired
    @Bean
    public DataSourceInitializer dataSourceInitializer(
            @Qualifier("dataSource") DataSource dataSource) {
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScript(new ClassPathResource("sql"));
        dataSourceInitializer.setDatabasePopulator(databasePopulator);
        dataSourceInitializer.setEnabled(false);

        return dataSourceInitializer;
    }

    @Autowired
    @Bean
    public DataSourceTransactionManager transactionManager(
            @Qualifier("dataSource") DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);

        return transactionManager;
    }

    @Autowired
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(
            @Qualifier("dataSource") DataSource dataSource,
            @Qualifier("mybatisProperties") MybatisProperties mybatisProperties) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(
                new DefaultResourceLoader());

        bean.setConfigLocation(resolver.getResource(mybatisProperties.getResource().getConfigLocation()));
        bean.setMapperLocations(resolver.getResources(mybatisProperties.getResource().getMapperLocationsPattern()));

        return new SqlSessionTemplate(bean.getObject());
    }

    @Bean
    @ConfigurationProperties(prefix = "mybatis")
    public MybatisProperties mybatisProperties() {
        return new MybatisProperties();
    }

    @SpringBootApplication
    public static class MybatisProperties {
        private ResourceProperties resource;

        public ResourceProperties getResource() {
            return resource;
        }

        public void setResource(ResourceProperties resource) {
            this.resource = resource;
        }

        public static class ResourceProperties {
            private String mapperLocationsPattern;
            private String configLocation;

            public String getMapperLocationsPattern() {
                return mapperLocationsPattern;
            }

            public void setMapperLocationsPattern(String mapperLocationsPattern) {
                this.mapperLocationsPattern = mapperLocationsPattern;
            }

            public String getConfigLocation() {
                return configLocation;
            }

            public void setConfigLocation(String configLocation) {
                this.configLocation = configLocation;
            }
        }
    }
}
