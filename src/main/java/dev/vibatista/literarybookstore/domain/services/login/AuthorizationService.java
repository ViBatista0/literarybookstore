//package dev.vibatista.literarybookstore.domain.services.login;
//
//import dev.vibatista.literarybookstore.domain.repositories.cliente.ClienteRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
///*
// *  Service de auth, precisa implementar a interface UserDetailsService e seus métodos.
// */
//@Service
//public class AuthorizationService implements UserDetailsService {
//
//    @Autowired
//    private ClienteRepository repository;
//
//    /* Carrega o método do nosso repository, e vai buscar o Cliente pelo email (username), só com isso o Spring
//     * já vain conseguir consultar o email do cliente na tabela.
//     * */
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return repository.findByLogin(username);
//    }
//}
