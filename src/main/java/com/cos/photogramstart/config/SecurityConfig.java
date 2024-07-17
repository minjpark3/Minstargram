package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**", "/api/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/auth/signin")
                .loginProcessingUrl("/auth/signin")
                .defaultSuccessUrl("/")
                .and()
                .exceptionHandling()
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                });
    }

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedDoubleSlash(true);
        return firewall;
    }
}
//@Configuration
//@EnableWebSecurity //해당파일로 시큐리티를 활성화
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Bean
//    public BCryptPasswordEncoder encoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http.csrf().disable();
//        http.authorizeRequests()
//                .antMatchers("/", "/user/**", "/image/**", "/subscribe/**, /comment/**","/api/**").authenticated()
//                .anyRequest()
//                .permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/auth/signin")//get
//                .loginProcessingUrl("/auth/signin")//post->스프링 시큐리티가 로그인 프레세스 진행
//                .defaultSuccessUrl("/");
//    }
//
//}