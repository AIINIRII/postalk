package xyz.aiinirii.postalk.config;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.context.annotation.Configuration;

/**
 * @author AIINIRII
 */
@Configuration
@MapperScan({"xyz.aiinirii.postalk.mapper"})
public class MybatisConfig {
}