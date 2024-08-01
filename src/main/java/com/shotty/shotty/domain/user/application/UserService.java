package com.shotty.shotty.domain.user.application;

import com.shotty.shotty.domain.user.domain.User;
import com.shotty.shotty.domain.user.dto.EncryptedUserDto;
import com.shotty.shotty.domain.user.dto.UserPatchDto;
import com.shotty.shotty.domain.user.dto.UserResponseDto;
import com.shotty.shotty.domain.user.exception.custom_exception.UserIdDuplicateException;
import com.shotty.shotty.domain.user.dao.UserRepository;
import com.shotty.shotty.domain.user.exception.custom_exception.UserNotFoundException;
import com.shotty.shotty.global.util.PatchUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void register(EncryptedUserDto encryptedUserDto) {
        try {
            userRepository.save(User.from(encryptedUserDto));
        } catch (DataIntegrityViolationException e) {
            throw new UserIdDuplicateException("이미 존재하는 사용자입니다.");
        }
    }

    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("존재하지 않는 사용자입니다.")
        );

        return UserResponseDto.from(user);
    }

    public UserResponseDto patch(Long id, UserPatchDto patchDto) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("존재하지 않는 사용자입니다.")
        );

        PatchUtil.applyPatch(user, patchDto);
        userRepository.save(user);

        return UserResponseDto.from(user);
    }
}
