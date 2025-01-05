package org.zxp.securitydemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private MyAuthenticationHandler myAuthenticationHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorize -> authorize
                        .requestMatchers(
//                                "/user/add",
//                                "/user/list",
                                "/login-page",
                                "/logout",
                                "/css/**",
                                "/js/**"
                        )
                        .permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login-page")                 // 指定登录页面路径
                        .loginProcessingUrl("/login")             // 登录表单提交路径
                        .defaultSuccessUrl("/index", true)    // 登录成功后的跳转页面
                        .failureUrl("/login-page?error")          // 登录失败后的跳转页面
                        .successHandler(myAuthenticationHandler)
                        .failureHandler(myAuthenticationHandler)
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .logoutSuccessHandler(myAuthenticationHandler)
                        .permitAll())
                .exceptionHandling(exception ->{
                    exception.authenticationEntryPoint(new MyAuthenticationEntryPoint());
                })
                .httpBasic(withDefaults());
//                .exceptionHandling(exceptions -> exceptions
//                        .authenticationEntryPoint((request, response, authException) -> {
//                            if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
//                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "未登录");
//                            } else {
//                                response.sendRedirect("/login-page");
//                            }
//                        })
//                );
        http.cors(withDefaults());
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
//        return manager;
//    }
//    @Bean
//    public UserDetailsService userDetailsService() {
//        DBUserDetailsManager dbUserDetailsManager = new DBUserDetailsManager();
////        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build())
//        return dbUserDetailsManager;
//    }
}
