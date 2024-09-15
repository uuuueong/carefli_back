package gradproject.carefli.oauth.controller;

import gradproject.carefuli.oauth.jwt.JwtService;
import gradproject.carefuli.oauth.jwt.JwtTokens;
import gradproject.carefuli.oauth.service.OAuthLoginService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/oauth2")
public class OAuthController {

    private final OAuthLoginService oAuthLoginService;
    private final JwtService jwtService;

    //jwt 토큰 생성 후 반환
    @ResponseBody
    @GetMapping("/kakao")
    public ResponseEntity<JwtTokens> kakaoCallback(@RequestParam(name="code") String code){
        return ResponseEntity.ok(oAuthLoginService.login(code));
    }

    //리프레시 토큰으로 액세스 토큰 재발급
    @PostMapping("/reissue")
    public ResponseEntity<JwtTokens> reissueAccessToken(HttpServletRequest request){
        return ResponseEntity.ok(jwtService.reissueAccessToken(request));
    }


}
