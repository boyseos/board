package com.board.web.user;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository{

    UserModel findByEmailAndPw(String email, String pw);

    Optional<UserModel> findByEmail(String email);
}