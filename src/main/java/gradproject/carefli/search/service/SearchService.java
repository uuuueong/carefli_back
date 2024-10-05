package gradproject.carefli.search.service;

import gradproject.carefli.connection.domain.Connection;
import gradproject.carefli.connection.repository.ConnectionRepository;
import gradproject.carefli.search.dto.SearchResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SearchService {
    private final ConnectionRepository connectionRepository;
    public ResponseEntity<?> searchByConnectionName(String connectionName) {
        List<Connection> connections = connectionRepository.findByWord(connectionName);
        List<SearchResponseDto> searchResponseDtos = connections.stream()
                .map(SearchResponseDto::from)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK)
                .body(searchResponseDtos);
    }
}
