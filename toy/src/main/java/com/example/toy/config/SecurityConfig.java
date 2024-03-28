package com.example.toy.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        // 권한에 따라 허용하는 url 설정
        // /login, /signup 페이지는 모두 허용, 다른 페이지는 인증된 사용자만 허용
        http
                .authorizeRequests() // 페이지 권한 설정
//                .antMatchers("/toy/create").hasAnyRole("USER", "MANAGER") // USER 및 MANAGER 권한이 있는 사용자만 접근 가능
                .antMatchers("/toy/create").authenticated()
                .antMatchers("/toy/delete").authenticated()
                .antMatchers("/login", "/toy", "/signUp").permitAll()
//                .antMatchers("/**").permitAll()
                .anyRequest().authenticated();

        // login 설정
        http
                .formLogin()
                .loginPage("/login")    // GET 요청 (login form을 보여줌)
                .loginProcessingUrl("/auth")    // POST 요청 (login 창에 입력한 데이터를 처리)
                .usernameParameter("EMPLOYEE_NO")	// login에 필요한 id 값을 email로 설정 (default는 username)
                .passwordParameter("EMPLOYEE_PW")	// login에 필요한 password 값을 password(default)로 설정
                .failureUrl("/members/login/error") // 로그인 실패할 시
                .defaultSuccessUrl("/toy", true);	// login에 성공하면 /로 redirect

        // logout 설정
        http
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/toy");	// logout에 성공하면 /로 redirect



        return http.build();
    }


//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().antMatchers("/static/**").anyRequest();
//    }
}

