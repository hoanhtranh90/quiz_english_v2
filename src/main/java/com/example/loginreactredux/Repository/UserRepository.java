package com.example.loginreactredux.Repository;

import com.example.loginreactredux.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("select u from User u where u.username like %:username%")
    Page<User> findByFirstnameEndsWith(String username, final Pageable pageable);


    @Query("select u from User u")
    Page<User> showAllUser(final Pageable pageable);

    @Query("select u from User u where u.id = :id")
    User findUById(Long id);
}