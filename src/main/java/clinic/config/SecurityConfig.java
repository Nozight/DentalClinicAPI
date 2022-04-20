package clinic.config;

import clinic.service.security.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AppUserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/appointment/**" ).hasAuthority("USER") //quiza no todos los endpoints deberian estar habilitados(puede borrar?)
                .antMatchers("/api/dentist/**", "/api/patient/**", "/api/appointment/**").hasAuthority("ADMIN")
                .antMatchers("/**").hasAuthority("USER")
                .antMatchers("/**").hasAuthority("ADMIN")

                .anyRequest()
                .authenticated().and()
                .formLogin()
                .permitAll()
                .defaultSuccessUrl("/homepage.html",true)
                .and().exceptionHandling().accessDeniedPage("/403");//Something went wrong page
         http   .headers().frameOptions().sameOrigin();

         /*http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/turnos/**").hasAuthority("USER")
                .antMatchers("/odontologos/**", "/pacientes/**").hasAuthority("ADMIN")
                .antMatchers("/index.html",
                        "/registroTurnos.html",
                        "/listaTurnos.html")
                .hasAuthority("USER")
                .antMatchers("/registroOdontologos.html",
                        "/registroPacientes.html",
                        "/index.html",
                        "/listaOdontologos.html",
                        "/listaPacientes.html")

                .hasAuthority("ADMIN")

                .anyRequest()
                .authenticated().and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403");

*/
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }


}