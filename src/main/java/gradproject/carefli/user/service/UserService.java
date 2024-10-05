package gradproject.carefli.user.service;

import gradproject.carefli.global.exception.CustomException;
import gradproject.carefli.global.exception.ErrorCode;
import gradproject.carefli.user.domain.User;
import gradproject.carefli.user.dto.UserUpdateRequestDto;
import gradproject.carefli.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND_BY_EMAIL));
    }


    /* 유저 정보 수정 */
    @Transactional
    public User updateUser(User user, UserUpdateRequestDto requestDto){
        // 닉네임 중복 체크
        if (!user.getNickname().equals(requestDto.getNickname()) &&
                userRepository.findByNickname(requestDto.getNickname()).isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
        }

        user.updateUser(requestDto.getNickname(), requestDto.getBirthday(), requestDto.getUserImageUrl(), requestDto.getInterestTag(), requestDto.getMbti());
        return userRepository.save(user);
    }


    /* 유저 삭제 */
    @Transactional
    public void deleteUser(User user){
        userRepository.delete(user);
    }
}
