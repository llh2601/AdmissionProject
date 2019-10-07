package ca.project.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity extends WebSecurityConfigurerAdapter{
	@Autowired
	private DataSource dataSource;
	 @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	         auth.jdbcAuthentication().dataSource(dataSource)
	        .usersByUsernameQuery("SELECT username,password,enabled FROM usuarios where username=?")
	        .authoritiesByUsernameQuery("select usuarios.username, roles.role from user_role " +
	    	        "inner join usuarios on usuarios.id=user_role.user_id "+
	    	        "inner join roles on roles.id=user_role.role_id " + "where usuarios.username=?");

	      
	        }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {

	    	 http	.authorizeRequests()
	    	 .antMatchers("/bootstrap/**","/tinymce/**","/resources/**", "/static/**", "/css/**", "/js/**", "/images/**","/webjars/**").permitAll()
             .antMatchers("/").permitAll()
	    	 .antMatchers("/users/**").hasAuthority("admin");    
             http.authorizeRequests().antMatchers("/**").authenticated().anyRequest().permitAll()
     		.and().formLogin().permitAll();
     		http.logout().invalidateHttpSession(true).logoutUrl("/logout");
  		 
   
            
 }

	  


}
