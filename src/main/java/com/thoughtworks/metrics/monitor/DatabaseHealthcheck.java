package com.thoughtworks.metrics.monitor;

import com.codahale.metrics.health.HealthCheck;
import com.mongodb.CommandResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseHealthCheck extends HealthCheck {

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Override
    protected Result check() {
        CommandResult stats = mongoTemplate.getDb().getStats();

        if(stats.ok()) {
            return HealthCheck.Result.healthy();
        } else {
            return HealthCheck.Result.unhealthy(stats.getErrorMessage());
        }
    }
}
