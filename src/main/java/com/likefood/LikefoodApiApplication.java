package com.likefood;

import com.likefood.pojo.common.ConstantValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@EntityScan(basePackages = {"com.likefood"})
@EnableJpaRepositories(basePackages = {"com.likefood"})
@ComponentScan(basePackages = {"com.likefood"})
@EnableJdbcHttpSession
@SpringBootApplication
public class LikefoodApiApplication extends WebMvcConfigurerAdapter {
//public class LikefoodApiApplication {
	@Value("${utils.savePath}")
	public String savePath;

	public static void main(String[] args) {
		SpringApplication.run(LikefoodApiApplication.class, args);
	}
	@Override
	public void configurePathMatch(PathMatchConfigurer matcher) {
		matcher.setUseRegisteredSuffixPatternMatch(true);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(ConstantValue.IMG_PATH + "/**")
				.addResourceLocations("/public", "file://" + savePath)
				.setCachePeriod(31556926);
	}
}
