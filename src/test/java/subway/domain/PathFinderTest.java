package subway.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import subway.domain.path.Path;
import subway.domain.path.PathFinder;
import subway.domain.section.Section;
import subway.domain.section.Sections;
import subway.domain.station.Station;
import subway.domain.subway.Subway;
import subway.jgrapht.JgraphtPathFinder;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class PathFinderTest {

    @Test
    void 최단_거리를_조회한다() {
        // given
        final Subway subway = new Subway(List.of(getSections1(), getSections2()));
        final PathFinder pathFinder = new JgraphtPathFinder(subway);

        // when
        final Path path = pathFinder.findShortestPath(new Station(1L, "잠실역"), new Station(3L, "신촌역"));

        // then
        final List<Station> expectedStations = List.of(new Station(1L, "잠실역"),
                new Station(2L, "아현역"),
                new Station(3L, "신촌역"));
        assertAll(
                () -> assertThat(path.getStations()).usingRecursiveComparison()
                        .isEqualTo(expectedStations),
                () -> assertThat(path.getDistance()).isEqualTo(3L)
        );
    }

    Sections getSections1() {
        final Station 잠실역 = new Station(1L, "잠실역");
        final Station 아현역 = new Station(2L, "아현역");
        final Station 신촌역 = new Station(3L, "신촌역");

        final Section section1 = new Section(1L, 잠실역, 아현역, 1L);
        final Section section2 = new Section(2L, 아현역, 신촌역, 2L);

        return new Sections(List.of(section1, section2), 1L);
    }

    Sections getSections2() {
        final Station 잠실역 = new Station(1L, "잠실역");
        final Station 신촌역 = new Station(3L, "신촌역");

        final Section section = new Section(3L, 잠실역, 신촌역, 10L);

        return new Sections(List.of(section), 2L);
    }
}