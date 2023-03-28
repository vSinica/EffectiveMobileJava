package ru.vados.effectivemobile.Config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"ru.vados.effectivemobile.Controller", "ru.vados.effectivemobile.Service"})
@EnableJpaRepositories(basePackages = "ru.vados.effectivemobile.Repository")
@EntityScan(basePackages = {"ru.vados.effectivemobile.Entity"})
public class ServiceConfig {

}
