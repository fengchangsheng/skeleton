package com.fcs.common.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Lucare.Feng on 2017/2/24.
 * update on 2017/07/13
 */
@Configuration
@MapperScan("com.fcs.*.mapper*")
public class MybatisPlusConfig {

    /**
     *	 mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
