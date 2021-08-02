package lt.tomasbarauskas.blog.configers;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder encoder;

    public SecurityConfig(DataSource dataSource, UserDetailsService userDetailsService, PasswordEncoder encoder) {
        this.dataSource = dataSource;
        this.userDetailsService = userDetailsService;
        this.encoder = encoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                  .antMatchers("/topic/create", "/topic/*/edit", "topic/*/delete").authenticated()
                  .anyRequest().permitAll()
                  .and()
                .formLogin()
                  .permitAll()
                  .loginPage("/login")
                  .loginProcessingUrl("/login")
                  .defaultSuccessUrl("/")
                  .failureUrl("/login?error=true")
                  .usernameParameter("username")
                  .passwordParameter("password")
                  .and()
                .logout()
                  .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }
}
