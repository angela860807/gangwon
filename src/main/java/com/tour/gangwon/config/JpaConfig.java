package com.tour.gangwon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 생성일 자동 처리
@Configuration
@EnableJpaAuditing
public class JpaConfig {
}
