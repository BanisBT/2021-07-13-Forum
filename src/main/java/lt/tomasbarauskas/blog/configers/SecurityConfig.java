package lt.tomasbarauskas.blog.configers;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
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
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("")
                .authoritiesByUsernameQuery("")
                .passwordEncoder(passwordEncoder);
    }
}
