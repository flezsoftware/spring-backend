package pl.flez.reactapi.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class WebConfig implements WebFluxConfigurer {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        // messageSource.setUseCodeAsDefaultMessage(true);
        //messageSource.setCacheSeconds(10); //reload messages every 10 seconds
        return messageSource;
    }

/*    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return new LocaleMessageCodeResolver(messageSource());
    }*/

    /*  DefaultMessageCodesResolver defaultMessageCodesResolver(){
        DefaultMessageCodesResolver defaultMessageCodesResolver = new DefaultMessageCodesResolver();
        return defaultMessageCodesResolver;
    }*/


}
