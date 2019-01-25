package com.example.ArticleProxy;

import javax.sql.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;


@EnableEurekaClient
@EnableZuulProxy
@EnableAutoConfiguration(exclude = {
    DataSourceAutoConfiguration.class})
@SpringBootApplication
public class ArticleProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArticleProxyApplication.class, args);
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(H2)
                .addScript("create.sql")
                .addScript("update.sql")
                .build();
    }//end dataSource
}
