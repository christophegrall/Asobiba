package net.orekyuu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class AsobibaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsobibaApplication.class, args);
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
}
