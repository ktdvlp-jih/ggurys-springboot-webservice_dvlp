package com.ggurys.springbootwebservice;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@RequiredArgsConstructor
//@EnableJpaRepositories
//@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
//@SpringBootApplication(scanBasePackages = {"com.ggurys.springbootwebservice"})
//@EnableJpaRepositories(basePackages = {"com.ggurys.springbootwebservice.domain.user"})

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
