package com.test.spring;

import com.test.repository.TestRepo;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


@SpringBootApplication
@EnableJpaRepositories("com.test.repository")
@EntityScan("com.test.beans")
public class Application {
    @Autowired
    private Environment env;

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Bean(name = "primary")
    @ConfigurationProperties(prefix = "spring.datasource.ketkee")
    @Primary
    public DataSource primaryDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondary")
    @ConfigurationProperties(prefix = "spring.datasource.vinay")
    public DataSource secondaryDataSource(){
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "secondary-template")
    public NamedParameterJdbcTemplate secondaryTemplate(@Autowired
                                                            @Qualifier("secondary")
                                                                DataSource secondary){
        return new NamedParameterJdbcTemplate(secondary);
    }

    @Bean
    public TestRepo testRepo(){
        return new TestRepo();
    }
}
