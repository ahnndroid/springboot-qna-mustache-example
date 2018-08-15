package com.ahnndroid.springbootqnamustacheexample.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahnndroid.springbootqnamustacheexample.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
