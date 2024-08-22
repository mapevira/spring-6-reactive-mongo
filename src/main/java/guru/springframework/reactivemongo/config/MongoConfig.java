package guru.springframework.reactivemongo.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;

/**
 * Created by jt, Spring Framework Guru.
 *
 * @author architecture - raulp
 * @version 22/08/2024 - 18:04
 * @since jdk 1.21
 */
@Configuration
public class MongoConfig extends AbstractReactiveMongoConfiguration {

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create();
    }

    @Override
    protected String getDatabaseName() {
        return "sfg";
    }

}
