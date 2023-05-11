package subway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import subway.dto.line.LineRequest;
import subway.dto.station.LineMapResponse;
import subway.entity.LineEntity;
import subway.service.LineService;
import subway.service.SubwayMapService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/lines")
public class LineController {

    private final LineService lineService;
    private final SubwayMapService subwayMapService;

    public LineController(final LineService lineService, final SubwayMapService subwayMapService) {
        this.lineService = lineService;
        this.subwayMapService = subwayMapService;
    }

    @PostMapping
    public ResponseEntity<Void> createLine(@RequestBody @Valid LineRequest lineRequest) {
        Long id = lineService.saveLine(lineRequest);
        return ResponseEntity.created(URI.create("/lines/" + id)).build();
    }

    @GetMapping
    public ResponseEntity<List<LineEntity>> findAllLines() {
        return ResponseEntity.ok(lineService.findAll());
    }

    @GetMapping("/{lineNumber}")
    public ResponseEntity<LineMapResponse> findLineById(@PathVariable final Long lineNumber) {
        return ResponseEntity.ok().body(subwayMapService.showLineMap(lineNumber));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLine(@PathVariable final Long id) {
        lineService.deleteLineById(id);
        return ResponseEntity.noContent().build();
    }
}