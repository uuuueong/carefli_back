package gradproject.carefli.gift.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GiftCreateService {

    private final JdbcTemplate jdbcTemplate;

    public GiftCreateService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void insertGift(String category, String subCategory, String giftName, int price, String giftUrl, String giftImageUrl) {
        String sql = "INSERT INTO gift (category, sub_category, gift_name, price, gift_url, gift_image_url) VALUES (?, ?, ?, ?, ?, ?)";

        // SQL 쿼리 실행
        jdbcTemplate.update(sql, category, subCategory, giftName, price, giftUrl, giftImageUrl);
    }
}
