package lt.tomasbarauskas.blog.configers;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                  .anyRequest().authenticated()
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
}
