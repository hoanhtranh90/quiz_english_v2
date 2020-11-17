package com.example.loginreactredux.Auth;

import com.example.loginreactredux.Dto.UserDto;
import com.example.loginreactredux.Model.Role;
import com.example.loginreactredux.Model.User;
import com.example.loginreactredux.Repository.RoleRepository;
import com.example.loginreactredux.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		Role role = user.getRole();
		authorities.add(new SimpleGrantedAuthority(role.getRole()));
//		for(Role role : user.getRoles()){
//			authorities.add(new SimpleGrantedAuthority(role.getRole()));
//		}

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				true,true,true,true,authorities);
	}
//save user
	public User save(UserDto user) {
		User u = userRepository.findByUsername(user.getUsername());

		if (u != null) {
			//loi tai khoan da ton tai
			throw new UsernameNotFoundException("abc");
		} else {
			User newUser = new User();
			newUser.setUsername(user.getUsername());
			newUser.setRole(roleRepository.findRoleById( (long) 1	));
			newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
			return userRepository.save(newUser);
		}
	}


}