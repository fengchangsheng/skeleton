package com.fcs.common;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Created by Lucare.Feng on 2017/2/24.
 */
@TestConfiguration
public class MybatisPlusConfig {

    /**
     *	 mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
