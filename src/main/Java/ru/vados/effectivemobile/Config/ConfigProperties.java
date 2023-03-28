package ru.vados.effectivemobile.Config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"ru.vados.effectivemobile"})
@PropertySource(value = {
        "classpath:config/application-main.yml"}, factory = YamlPropertySourceFactory.class)
public class ConfigProperties {
}
