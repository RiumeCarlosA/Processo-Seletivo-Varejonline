package br.com.varejonline.riume.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.varejonline.riume.model.usuarios.Pessoa;
import br.com.varejonline.riume.repository.usuarios.PessoaRepository;
import br.com.varejonline.riume.security.UserSS;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private PessoaRepository repository;

	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		Optional<Pessoa> user = repository.findByUsuario(usuario);
		System.out.println(user.get().getNome());
		if(user.isPresent()) {
			return new UserSS(user.get().getId(), user.get().getUsuario(), user.get().getSenha(), user.get().getPerfis());
		}
		throw new UsernameNotFoundException(usuario);
	}

}
