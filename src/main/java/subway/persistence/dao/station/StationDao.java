package subway.persistence.dao.station;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import subway.persistence.entity.station.StationEntity;

@Repository
public class StationDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertAction;

    private final RowMapper<StationEntity> rowMapper = (rs, rowNum) ->
            new StationEntity(
                    rs.getLong("station_id"),
                    rs.getString("name")
            );

    public StationDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertAction = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("station")
                .usingGeneratedKeyColumns("station_id");
    }

    public boolean isIdExist(final Long id) {
        String sql = "SELECT EXISTS(SELECT 1 FROM station WHERE station_id = ?)";
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, id));
    }

    public boolean isNameExist(final String name) {
        String sql = "SELECT EXISTS(SELECT 1 FROM station WHERE name = ?)";
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, name));
    }

    public Long save(final StationEntity stationEntity) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", stationEntity.getName());
        return insertAction.executeAndReturnKey(params).longValue();
    }

    public void deleteById(final Long id) {
        String sql = "DELETE FROM station WHERE station_id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<StationEntity> findAll() {
        String sql = "SELECT station_id, name FROM station";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public StationEntity findById(final Long id) {
        String sql = "SELECT station_id, name FROM station WHERE station_id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public StationEntity findByName(final String name) {
        String sql = "SELECT station_id, name FROM station WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, name);
    }
}