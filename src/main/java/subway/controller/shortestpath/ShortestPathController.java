package subway.controller.shortestpath;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import subway.dto.shortestpath.ShortestPathRequest;
import subway.dto.shortestpath.ShortestPathResponse;
import subway.service.shortestpath.ShortestPathService;

@RequestMapping("/shortest-path")
@RestController
public class ShortestPathController {

    private final ShortestPathService shortestPathService;

    public ShortestPathController(final ShortestPathService shortestPathService) {
        this.shortestPathService = shortestPathService;
    }

    @GetMapping
    ResponseEntity<ShortestPathResponse> showShortestPath(@RequestBody @Valid ShortestPathRequest request) {
        final ShortestPathResponse shortestPath = shortestPathService.findShortestPath(request);
        return ResponseEntity.ok(shortestPath);
    }
}
