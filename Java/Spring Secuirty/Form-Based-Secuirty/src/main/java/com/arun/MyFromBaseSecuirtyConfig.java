package com.arun;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.arun.service.UserDetailsServiceDataBaseImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MyFromBaseSecuirtyConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsServiceDataBaseImpl userDetailsServiceDataBaseImpl;
	
	
	public MyFromBaseSecuirtyConfig(UserDetailsServiceDataBaseImpl userDetailsServiceDataBaseImpl) {
		this.userDetailsServiceDataBaseImpl = userDetailsServiceDataBaseImpl;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/singup").permitAll().anyRequest().authenticated().and().formLogin().loginPage("/singup").loginProcessingUrl("/dologin").defaultSuccessUrl("/getDataTest");
		//.csrf().disable() if requeired
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceDataBaseImpl).passwordEncoder(passwordEncoder());
	}

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}
