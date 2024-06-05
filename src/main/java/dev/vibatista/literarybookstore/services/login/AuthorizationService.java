package dev.vibatista.literarybookstore.services.login;

import dev.vibatista.literarybookstore.repositories.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
 *  Service de auth, precisa implementar a interface UserDetailsService e seus métodos.
 */
@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private ClienteRepository repository;

    // Carrega o método do nosso repository, e vai buscar o Cliente pelo email (username)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username);
    }
}
