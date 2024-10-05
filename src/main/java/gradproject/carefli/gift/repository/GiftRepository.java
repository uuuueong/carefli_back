package gradproject.carefli.gift.repository;

import gradproject.carefli.gift.domain.Gift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GiftRepository extends JpaRepository<Gift, Long> {
    List<Gift> findAllByPriceBetween(int minPrice, int maxPrice);
    List<Gift> findAllByGiftIdIn(List<Long> giftIds);
}
