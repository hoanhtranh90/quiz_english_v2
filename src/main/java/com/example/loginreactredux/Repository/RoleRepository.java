package com.example.loginreactredux.Repository;

import com.example.loginreactredux.Model.Role;
import com.example.loginreactredux.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    //lay danh sach user theo role
//    @Query("select u from User u inner join u.role r where r.role like %:role%")
//    Page<User> findByRole(String role, final Pageable pageable);


    @Query("select r from role r where r.id = :id")
    Role findRoleById(Long id);

    @Query("select r.id from role r where r.role like %:name%")
    long findRoleByName(String name);
}
