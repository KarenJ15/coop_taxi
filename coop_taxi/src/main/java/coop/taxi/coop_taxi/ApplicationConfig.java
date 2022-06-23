package coop.taxi.coop_taxi;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class ApplicationConfig {
    @Bean
    public ModelMapper modelMapped(){
        return new ModelMapper();
    }
}
