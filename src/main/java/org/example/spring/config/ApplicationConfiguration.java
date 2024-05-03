package org.example.spring.config;

import org.example.spring.database.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration()
//@PropertySource("classpath:application.properties")
//@ImportResource
//@Import()
public class ApplicationConfiguration {

        @Bean
        public ConnectionPool pool2(@Value("${db.username}") String username,
                                    @Value("${db.password}") String password) {
                return new ConnectionPool(username,20, password);
        }

        @Bean
        @Scope(BeanDefinition.SCOPE_PROTOTYPE)
        public ConnectionPool pool3() {
                return new ConnectionPool("test-pool",20, "post");
        }
}
