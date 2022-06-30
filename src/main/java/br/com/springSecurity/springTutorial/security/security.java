package br.com.springSecurity.springTutorial.security;

import br.com.springSecurity.springTutorial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class security extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Autowired
    TokenService tokenService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserDetailCustomService userDetailCustomService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailCustomService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.authorizeRequests()
//                .antMatchers(HttpMethod.GET,"/produtos").permitAll()
//                .antMatchers(HttpMethod.GET,"/produtos/*").permitAll()
//                .antMatchers(HttpMethod.GET,"/users").permitAll()
//                .antMatchers(HttpMethod.POST,"/users").permitAll()
//                .antMatchers(HttpMethod.GET,"/users/*").permitAll()
//                .antMatchers(HttpMethod.GET,"/roles").permitAll()
//                .antMatchers(HttpMethod.GET,"/roles/*").permitAll()
//                .anyRequest().authenticated()
//                .and().formLogin();

//                http
//                        .authorizeRequests()
//                        .antMatchers(HttpMethod.GET,"/user").permitAll()
//                        .antMatchers(HttpMethod.GET,"/user").permitAll()
//                        .antMatchers(HttpMethod.GET,"/user/*").permitAll()
//                        .antMatchers(HttpMethod.GET,"/produtos/*").permitAll()
//                        .antMatchers(HttpMethod.GET,"/produtos/**").permitAll()
//                        .antMatchers(HttpMethod.GET,"/role/*").permitAll()
//                        .antMatchers(HttpMethod.POST,"/user").permitAll()
//                        .antMatchers(HttpMethod.POST,"/role").permitAll()
//                        .antMatchers(HttpMethod.POST,"/auth").permitAll()
//                        .anyRequest().authenticated()
//                        .and().csrf().disable()
//                        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                        .and().addFilterBefore(new AuthenticationFilter(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class);

             http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/user").permitAll()
                .antMatchers(HttpMethod.GET,"/user").permitAll()
                .antMatchers(HttpMethod.GET,"/user/*").permitAll()
                .antMatchers(HttpMethod.GET,"/produtos/*").permitAll()
                .antMatchers(HttpMethod.GET,"/produtos/**").permitAll()
                .antMatchers(HttpMethod.GET,"/role/*").permitAll()
                .antMatchers(HttpMethod.POST,"/user").permitAll()
                .antMatchers(HttpMethod.POST,"/role").permitAll()
                .antMatchers(HttpMethod.POST,"/auth").permitAll()
                .anyRequest().authenticated()
                .and().cors()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AuthenticationFilter(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
