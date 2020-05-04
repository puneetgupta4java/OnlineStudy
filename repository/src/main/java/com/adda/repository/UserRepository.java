package com.adda.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.adda.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   User findByEmail(String email);
   User findByEmailAndPassword(String email, String password);
   @Query("select u from User u where (name like %?1% or email like %?1%) and accountState=?2")
   Page<User> findByNameOrEmail(String property, String accState, Pageable pageable);
}
