package pl.flez.reactapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import javax.validation.Validator;


@Configuration
@EnableMongoAuditing
public class MongoConfig {

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener(Validator webFluxValidator) {
        return new ValidatingMongoEventListener(webFluxValidator);
    }
}
