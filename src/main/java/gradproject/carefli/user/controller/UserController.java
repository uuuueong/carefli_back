package gradproject.carefli.user.controller;

import gradproject.carefli.oauth.customAnnotation.AuthUser;
import gradproject.carefli.oauth.service.KakaoApiClient;
import gradproject.carefli.user.domain.User;
import gradproject.carefli.user.dto.UserResponseDto;
import gradproject.carefli.user.dto.UserUpdateRequestDto;
import gradproject.carefli.user.dto.UserUpdateResponseDto;
import gradproject.carefli.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final KakaoApiClient kakaoApiClient;

    /* 회원 정보 조회 */
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public UserResponseDto getUser(@AuthUser User user){
        return UserResponseDto.from(user);
    }

    /* 회원 정보 수정 */
    @PatchMapping
    @ResponseStatus(value = HttpStatus.OK)
    public UserUpdateResponseDto updateUser(@AuthUser User user,
                                            @RequestBody @Valid final UserUpdateRequestDto requestDto) {
        User updatedUser = userService.updateUser(user, requestDto);
        return UserUpdateResponseDto.from(updatedUser);
    }

    /* 회원 탈퇴 */
    @DeleteMapping
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteUser(@AuthUser User user) {
        userService.deleteUser(user);
        String response = kakaoApiClient.UnlinkUser(user.getKakaoAccessToken());
        return "성공적으로 탈퇴되었습니다. 카카오계정ID : " + response;
    }

}
