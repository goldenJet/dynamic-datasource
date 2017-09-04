package com.example.demo.config.dataBase;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 数据源配置
 * Created by Jet.chen on 2017/9/4.
 */
@Data
@Component
@ConfigurationProperties(prefix = "hikaricp")
public class DataBaseProperties {
    private HikariDataSource test1;
    private HikariDataSource test2;
}
