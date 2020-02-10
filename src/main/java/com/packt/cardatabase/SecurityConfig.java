package com.packt.cardatabase;

import com.packt.cardatabase.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration // switch off default config
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {



  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .csrf()
        .disable() //disalbe csrf protection
        .cors()//  Cross-Origin Resource Sharing (CORS
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/login")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .addFilterBefore(
            new LoginFilter("/login", authenticationManager()),
            UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
  }

   @Autowired private UserDetailServiceImpl userDetailService;
   @Autowired
   public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder)
      throws Exception {
      authenticationManagerBuilder
          .userDetailsService(userDetailService)
          .passwordEncoder(new BCryptPasswordEncoder());
   }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource =
        new UrlBasedCorsConfigurationSource();
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
    corsConfiguration.setAllowedMethods(Arrays.asList("*"));
    corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
    corsConfiguration.setAllowCredentials(true);
    corsConfiguration.applyPermitDefaultValues();
    urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration); // allow all path
    return urlBasedCorsConfigurationSource;
  }

  //    no more load credential from  memory
  //    @Override
  //    protected void configure(HttpSecurity http) throws Exception {
  //        super.configure(http);
  //    }
  //    @Bean
  //    @Override
  //    public UserDetailsService userDetailsService() {
  //        UserDetails userDetails =
  // User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build();
  //        return new InMemoryUserDetailsManager(userDetails);
  //    }

}
