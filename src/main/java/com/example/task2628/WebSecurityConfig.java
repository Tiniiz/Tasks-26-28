package com.example.task2628;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    UserService userService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests().requestMatchers("/").permitAll();
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                //Доступ только для не зарегистрированных пользователей
                .requestMatchers("/registration").permitAll()
                //Доступ только для пользователей с ролью Администратор
                .requestMatchers("/edit/**").hasRole("ADMIN")
                .requestMatchers("/create").hasRole("ADMIN")
                .requestMatchers("/delete/**").hasRole("ADMIN")
                .requestMatchers("/blog/delete/**").hasRole("ADMIN")
                .requestMatchers("/blog/edit/**").hasRole("ADMIN")
                .requestMatchers("/blog/create/**").hasRole("ADMIN")
                .requestMatchers("/admin_panel/**").hasRole("ADMIN")
                .requestMatchers("/findall").permitAll()
                //Доступ разрешен всем пользователей
                .requestMatchers("/", "/resources/**").permitAll()
                //Все остальные страницы требуют аутентификации
                .anyRequest().authenticated()
                .and()
                //Настройка для входа в систему
                .formLogin()
                .loginPage("/login")
                //Перенарпавление на главную страницу после успешного входа
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/");
        return http.build();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }
}

