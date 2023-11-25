package br.iftm.edu.baoOuNao.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfiguration  implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedMethods("*")
                //  .allowedOrigins("http://localhost:3000");
                .allowedOrigins("*")
                .allowedHeaders("*");
        //.allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS", "HEAD", "TRACE", "CONNECT");

    }
}