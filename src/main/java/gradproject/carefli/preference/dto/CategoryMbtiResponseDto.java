package gradproject.carefli.preference.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class CategoryMbtiResponseDto {
    private String category;
    private Map<String, Long> mbtiPreferences;
}
