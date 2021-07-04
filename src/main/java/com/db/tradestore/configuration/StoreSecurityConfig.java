package com.db.tradestore.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.db.tradestore.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class StoreSecurityConfig extends WebSecurityConfigurerAdapter
{
   private final AuthenticationEntryPoint entryPoint;
   private final UserDetailsServiceImpl userDetailsService;

   @Autowired
   public StoreSecurityConfig(final AuthenticationEntryPoint entryPoint, final UserDetailsServiceImpl userDetailsService)
   {
      this.entryPoint = entryPoint;
      this.userDetailsService = userDetailsService;
   }

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception
   {
      auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception
   {
      http.cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(
            SessionCreationPolicy.STATELESS)
            .and()
            .requestMatchers().antMatchers("/api/v1/**")
            .and()
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .httpBasic().authenticationEntryPoint(entryPoint);
      http.headers().frameOptions().sameOrigin();
   }
}
