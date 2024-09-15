package gradproject.carefli.oauth.jwt;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtRefreshTokenRepository extends JpaRepository<JwtRefreshToken, Long> {
    JwtRefreshToken findByUserId(Long userId);
}
