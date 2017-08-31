package com.cricteam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = "com.cricteam")
@EntityScan(basePackages ="com.cricteam")
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.cricteam")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
