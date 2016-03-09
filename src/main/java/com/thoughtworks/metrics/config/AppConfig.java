package com.thoughtworks.metrics.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Configuration
@PropertySources({
        @PropertySource(value = "classpath:application.properties"),
        @PropertySource(value = "file:/etc/metrics-spring/application.properties", ignoreResourceNotFound=true)
})
public class AppConfig {
    @Autowired
    private Environment environment;

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        String mongoHost = environment.getProperty("mongo.host");
        String mongoDatabase = environment.getProperty("mongo.database");

        return new SimpleMongoDbFactory(new MongoClient(mongoHost), mongoDatabase);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
