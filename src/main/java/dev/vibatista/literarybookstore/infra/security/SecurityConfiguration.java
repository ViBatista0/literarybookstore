package dev.vibatista.literarybookstore.infra.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // Fala para o Spring que essa classe é de configuração
@EnableWebSecurity
//// Habilita a segurança web do Spring Security, podemos personalizar o config do aplicativo nessa class
public class SecurityConfiguration {

//    @Autowired
//    SecurityFilter securityFilter;
//
//    /* Anotação para o Spring conseguir instanciar nosso objeto, ele é um "bean", e vai ser instanciado pelo
//     * container do Spring.
//     * */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // csfr é uma config padrão do Spring baseada em token.
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests.anyRequest().permitAll()  // Permite todas as requisições sem autenticação
                )
                // definimos a política de segurança como stateless, ou seja, não vai armazenar dados do user
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(autorize -> autorize
//                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
//                        .anyRequest().authenticated()

//                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();}
    }
//
//    // Método para retornar um AuthenticationManager
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    // Método de criptografia de senha (hash)
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//}

