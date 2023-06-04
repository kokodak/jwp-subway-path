package subway.service;

import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import subway.domain.line.Line;
import subway.domain.station.Station;
import subway.dto.section.SectionCreateRequest;
import subway.dto.station.StationResponse;
import subway.persistence.repository.line.JdbcLineRepository;
import subway.persistence.repository.station.JdbcStationRepository;
import subway.service.line.LineMapService;
import subway.service.section.SectionService;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Transactional
@SpringBootTest
public class LineMapServiceTest {

    @Autowired
    private LineMapService lineMapService;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private JdbcLineRepository lineRepository;

    @Autowired
    private JdbcStationRepository stationRepository;

    @Test
    void 지하철_노선도를_조회한다() {
        final Long lineId = lineRepository.save(new Line(2L, "2호선", "초록색"));
        stationRepository.save(new Station("잠실역"));
        stationRepository.save(new Station("아현역"));
        stationRepository.save(new Station("신촌역"));
        sectionService.addSection(new SectionCreateRequest(2L, "잠실역", "아현역", 5L));
        sectionService.addSection(new SectionCreateRequest(2L, "잠실역", "신촌역", 3L));

        final List<StationResponse> stationResponses = lineMapService.findById(lineId).getStations();

        final SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(stationResponses.get(0).getName()).isEqualTo("잠실역");
        softAssertions.assertThat(stationResponses.get(1).getName()).isEqualTo("신촌역");
        softAssertions.assertThat(stationResponses.get(2).getName()).isEqualTo("아현역");
        softAssertions.assertAll();
    }
}