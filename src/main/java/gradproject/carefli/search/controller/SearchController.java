package gradproject.carefli.search.controller;

import gradproject.carefli.oauth.customAnnotation.AuthUser;
import gradproject.carefli.search.service.SearchService;
import gradproject.carefli.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @GetMapping
    public ResponseEntity<?> search(@AuthUser User user, @RequestParam String connectionName) {
        return searchService.searchByConnectionName(user.getUserId(), connectionName);
    }
}
