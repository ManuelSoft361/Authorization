package pe.com.manuelAutorizacion.authorization.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
public class SecurityConfig {

   /* @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{

        return http
                .httpBasic()
                .and().authorizeHttpRequests()
                //.anyRequest().permitAll() //todos los permisos
                //.anyRequest().denyAll() // todos denegados el recurso
                //.anyRequest().authenticated() // solo los que se autentican
                //.anyRequest().hasRole("ADMIN") // acceden solo por rol authorizados
                //anyRequest().hasAuthority("read") // por tipo de autorizacion
                .anyRequest().access(new WebExpressionAuthorizationManager("hasRole('ADMIN') or hasRole('DBA')"))//sPRING eXPRESION LANGUAGE
                .and().build();
    }*/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{

        return http
                .httpBasic()
                .and().authorizeHttpRequests()
                //.requestMatchers("/demo").permitAll()
                //.requestMatchers("/admin").hasRole("ADMIN")
                //.requestMatchers("/dba").hasAnyRole("DBA","ADMIN")
                //.and().build(); hasta aqui funciona solo este instruccion
                .requestMatchers(HttpMethod.POST,"/add").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/add").authenticated()
                .and().csrf().disable().build();


    }
    @Bean
    public UserDetailsService userDetailsService(){


        return new InMemoryUserDetailsManager(
                User.withUsername("user")
                        .password(passwordEncoder().encode("passsword"))
                        .authorities("read","ROLE_USER")
                        .build(),
                User.withUsername("admin")
                        .password(passwordEncoder().encode("passsword"))
                        .authorities("read","write","ROLE_ADMIN")
                        .build(),
                User.withUsername("dba")
                        .password(passwordEncoder().encode("passsword"))
                        .authorities("read","ROLE_DBA")
                        .build()


        );
    }




    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
