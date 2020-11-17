package com.example.loginreactredux.Service;

import com.example.loginreactredux.Dto.UserDto;
import com.example.loginreactredux.Model.User;
import com.example.loginreactredux.Repository.RoleRepository;
import com.example.loginreactredux.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public Page<User> showDataSeach(String name, Pageable pageable) {
        return userRepository.findByFirstnameEndsWith(name,pageable);

    }


    public Page<User> showDataPage(Pageable pageable) {
        return userRepository.showAllUser(pageable);
    }

    public User updateAccount(Long id, UserDto userDto){
        User user = userRepository.findUById(id);
        if(userRepository.findByUsername(userDto.getUsername()) != null) {
            user.setUsername(userDto.getUsername());
            user.setPassword(bcryptEncoder.encode(userDto.getPassword()));
//        user.setRoles(userDto.); //chua co ham get role

            return userRepository.save(user);
        }
        else {
            throw new UsernameNotFoundException("abc");
        }

    }


//    public Page<User> findUserByRole(String role, Pageable pageable) {
//        return roleRepository.findByRole(role,pageable);
//    }

    public void deleteAccount(Long user_id) {
        User u = userRepository.findUById(user_id);

        userRepository.delete(u);
    }
}
