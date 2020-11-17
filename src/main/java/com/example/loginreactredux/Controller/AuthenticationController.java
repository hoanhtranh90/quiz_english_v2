package com.example.loginreactredux.Controller;


import com.example.loginreactredux.Auth.JwtTokenUtil;
import com.example.loginreactredux.Auth.JwtUserDetailsService;
import com.example.loginreactredux.Dto.LoginRequestDto;
import com.example.loginreactredux.Dto.UserDto;
import com.example.loginreactredux.Model.User;
import com.example.loginreactredux.Repository.UserRepository;
import com.example.loginreactredux.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;


	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	//	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequestDto authenticationRequest) throws Exception {
		System.out.println(authenticationRequest.getPassword());
		System.out.println(authenticationRequest.getUsername());
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);
		HashMap object = new HashMap();
//		object.put("token", String.valueOf(new JwtResponse(token)));
//		object.put("username", userDetails.getUsername());
//		object.put("Authorization", userDetails.getAuthorities().toString());

//		return ResponseEntity.ok(new JwtResponse(token));
		User user = userRepository.findByUsername(userDetails.getUsername());
		object.put("user",user);
		object.put("token",token);
		return ResponseEntity.ok(object);
	}

	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody UserDto user) {
		userDetailsService.save(user);
		return ResponseEntity.ok(userRepository.findByUsername(user.getUsername()));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (Exception e) {
			throw new Exception("ABCDEF");
		}
	}
	@PutMapping("/authenticate")
	public ResponseEntity<?> UpdateAcc(@RequestParam(value = "id", required = true) Long user_id,
									   @RequestBody UserDto userDto) {

		userService.updateAccount(user_id, userDto);

		return new ResponseEntity("Update thanh cong", HttpStatus.OK);
	}

	@DeleteMapping("/authenticate")
	public ResponseEntity<?> DeleteAcc(@RequestParam(value = "id", required = true) Long user_id) {

		userService.deleteAccount(user_id);


		return new ResponseEntity("Delete thanh cong", HttpStatus.OK);
	}
}
