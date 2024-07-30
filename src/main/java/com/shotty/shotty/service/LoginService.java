package com.shotty.shotty.service;

import com.shotty.shotty.Domain.User;
import com.shotty.shotty.exception.LoginFailureException;
import com.shotty.shotty.repository.UserRepository;
import com.shotty.shotty.util.Hasher;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    public User login(String userId, String password) {

        //db조회
        User user = userRepository.findByUserId(userId).orElseThrow(()->{throw new LoginFailureException("로그인실패:회원 존재 x");});
        //로그인 실패
        if(!BCrypt.checkpw(password, user.getPassword())) {
            throw new LoginFailureException("로그인실패:잘못된 비밀번호 ");
        }

        return user;
    }


}