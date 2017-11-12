package br.com.ifood.backend.advanced.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Configura para que os serviços do SpringActuator sejam acessados mediante
		// autenticação.
		http.authorizeRequests().antMatchers("/admin/**").authenticated().and().formLogin().permitAll().and().logout()
				.permitAll();
	}

}