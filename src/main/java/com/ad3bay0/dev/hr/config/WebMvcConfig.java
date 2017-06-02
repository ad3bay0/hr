/**
 * 
 */
package com.ad3bay0.dev.hr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Adebayo Adeniyan
 * 1 Jun 2017
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		
		return bCryptPasswordEncoder;
		
	}

}
