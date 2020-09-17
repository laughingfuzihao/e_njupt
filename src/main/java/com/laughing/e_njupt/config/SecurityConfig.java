package com.laughing.e_njupt.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 2020/8/6 17:01
 */
// prePostEnabled 方法执行之前拦截
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    public void configure(WebSecurity web) throws Exception {
        //  web.ignoring 不进行拦截（一般对于静态文件）
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/favicon.ico");
        web.ignoring().antMatchers("/order/**","/order/add/kd", "/user/**", "/worker/**");

    }

    /**
     * 用户授权方法1
     *
     * @return
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin")
                .password(new BCryptPasswordEncoder().encode("gwssi"))
                .roles("admin")
                .and()
                .withUser("才高八斗兵马俑")
                .password(new BCryptPasswordEncoder().encode("superlovers20."))
                .roles("admin");

    }


    /**
     * 安全拦截机制
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/order/**").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/worker/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .authorizeRequests()
                .and()
                .formLogin()
                .successForwardUrl("/page/table")
                .and()
                .httpBasic();
    }
}
