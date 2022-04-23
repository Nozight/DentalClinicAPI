package clinic.security.config;

import clinic.security.jwt.JwtAuthenticationEntryPoint;
import clinic.security.jwt.JwtRequestFilter;
import clinic.security.jwt.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override//Do not use configureGlobal, not @Autowired or circular reference ocurrs
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {return new BCryptPasswordEncoder();}

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure( HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests().antMatchers(HttpMethod.GET,"/api/dentist/**").hasAnyAuthority("ADMIN","USER")
                .and()
                .authorizeRequests().antMatchers( "/api/dentist/**").hasAuthority("ADMIN")
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET,"/api/patient/**").hasAnyAuthority("ADMIN","USER")
                .and()
                .authorizeRequests().antMatchers("/api/patient/**").hasAuthority("ADMIN")
                .and()
                .authorizeRequests().antMatchers("/api/appointment/**").hasAnyAuthority("ADMIN","USER")
                .and()
                .authorizeRequests().antMatchers("/authenticate").permitAll().anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }


}