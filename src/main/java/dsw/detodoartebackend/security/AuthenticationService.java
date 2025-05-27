package dsw.detodoartebackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dsw.detodoartebackend.repository.PersonaRepository;

@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    private PersonaRepository personaRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return personaRepository.findByUsername(username);
    }
}
