package gradproject.carefli.oauth.service;

import gradproject.carefli.oauth.dto.KakaoInfoResponseDto;
import gradproject.carefli.oauth.jwt.JwtService;
import gradproject.carefli.oauth.jwt.JwtTokens;
import gradproject.carefli.user.repository.UserRepository;
import gradproject.carefli.user.service.UserService;
import gradproject.carefli.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {
    private final UserRepository userRepository;
    private final KakaoApiClient kakaoApiClient;
    private final JwtService jwtService;
    private final UserService userService;

    public JwtTokens login(String code) {
        String kakaoAccessToken = kakaoApiClient.getAccessToken(code);
        KakaoInfoResponseDto kakaoInfoResponseDto = kakaoApiClient.getUserInfo(kakaoAccessToken);
        Long userId = findOrCreateUser(kakaoInfoResponseDto);
        saveKakaoAccessToken(userId, kakaoAccessToken);
        return jwtService.generateJwtToken(userId);
    }


    // 이메일로 회원 찾기
    private Long findOrCreateUser(KakaoInfoResponseDto kakaoInfoResponseDto) {
        return userRepository.findByEmail(kakaoInfoResponseDto.getEmail())
                .map(User::getUserId)
                .orElseGet(() -> newUser(kakaoInfoResponseDto)); // 없으면 회원 생성
    }

    // 회원 생성
    private Long newUser(KakaoInfoResponseDto kakaoInfoResponseDto) {
        String nickname = createInitialNickname();
        User user = User.builder()
                .email(kakaoInfoResponseDto.getEmail())
                .nickname(nickname)
                .build();
        return userRepository.save(user).getUserId();
    }

    // 초기 닉네임 생성
    private String createInitialNickname(){
        int randomNumber;
        String nickname;
        do {
            randomNumber = (int)(Math.random() * 100000);
            nickname = "케어플리" + randomNumber ;
        } while (userRepository.findByNickname(nickname).isPresent());
        return nickname;
    }


    // 카카오 액세스 토큰 저장
    private void saveKakaoAccessToken(Long userId, String kakaoAccessToken){
        User user = userRepository.findByUserId(userId);
        user.updateKakaoAccessToken(kakaoAccessToken);
        userRepository.save(user);
    }

}
