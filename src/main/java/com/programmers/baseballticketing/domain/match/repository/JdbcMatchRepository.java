package com.programmers.baseballticketing.domain.match.repository;

import static com.programmers.baseballticketing.domain.match.repository.MatchSql.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.programmers.baseballticketing.common.exception.DeleteFailException;
import com.programmers.baseballticketing.common.exception.NotFoundException;
import com.programmers.baseballticketing.common.exception.SaveFailException;
import com.programmers.baseballticketing.common.exception.UpdateFailException;
import com.programmers.baseballticketing.domain.match.model.Match;
import com.programmers.baseballticketing.domain.stadium.model.Stadium;

@Repository
public class JdbcMatchRepository implements MatchRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public JdbcMatchRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Long save(Match match) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		try {
			jdbcTemplate.update(INSERT.getSql(), new MapSqlParameterSource(toMatchParamMap(match)), keyHolder);
		} catch (DataAccessException e) {
			throw new SaveFailException();
		}

		return keyHolder.getKey().longValue();
	}

	@Override
	public Match findById(Long id) {
		Match match;
		try {
			match = jdbcTemplate.queryForObject(
				"select * from matches where id = :id",
				Collections.singletonMap("id", id),
				matchRowMapper);
		} catch (DataAccessException e) {
			throw new NotFoundException();
		}
		return match;
	}

	@Override
	public List<Match> findBy(LocalDateTime startTime, String stadiumName, String team) {
		Map<String, Object> queryMap = new HashMap<>();
		StringBuilder queryBuilder = new StringBuilder("select * from matches where 1=1");

		if (startTime != null) {
			queryBuilder.append(" and start_time = :startTime");
			queryMap.put("startTime", startTime);
		}
		if (stadiumName != null) {
			queryBuilder.append(" and stadium_id = :stadiumId");
			queryMap.put("stadiumId", Stadium.getStadiumByName(stadiumName).getId());
		}
		if (team != null) {
			queryBuilder.append(" and (home_team_name = :team or away_team_name = :team)");
			queryMap.put("team", team);
		}

		return jdbcTemplate.query(queryBuilder.toString(), queryMap, matchRowMapper);
	}

	@Override
	public List<Match> findAll() {
		return jdbcTemplate.query(SELECT_ALL.getSql(), matchRowMapper);
	}

	@Override
	public void update(Match updatedMatch) {
		try {
			jdbcTemplate.update(UPDATE.getSql(), toMatchParamMap(updatedMatch));
		} catch (DataAccessException e) {
			throw new UpdateFailException();
		}
	}

	@Override
	public void deleteById(Long id) {
		try {
			jdbcTemplate.update(DELETE_BY_ID.getSql(), Collections.singletonMap("id", id));
		} catch (DataAccessException e) {
			throw new DeleteFailException();
		}
	}

	@Override
	public void deleteAll() {
		try {
			jdbcTemplate.update(DELETE_ALL.getSql(), Collections.emptyMap());
		} catch (DataAccessException e) {
			throw new DeleteFailException();
		}
	}

	private Map<String, Object> toMatchParamMap(Match match) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", match.getId());
		paramMap.put("startTime", match.getStartTime());
		paramMap.put("homeTeamName", match.getHomeTeamName());
		paramMap.put("awayTeamName", match.getAwayTeamName());
		paramMap.put("leftSeatsCount", match.getLeftSeatsCount());
		paramMap.put("stadiumId", match.getStadiumId());
		return paramMap;
	}

	private RowMapper<Match> matchRowMapper = (rs, rowNum) -> {
		Long id = rs.getLong("id");
		LocalDateTime startTime = rs.getTimestamp("start_time").toLocalDateTime();
		String homeTeamName = rs.getString("home_team_name");
		String awayTeamName = rs.getString("away_team_name");
		Integer leftSeatsCount = rs.getInt("left_seats_count");
		Long stadiumId = rs.getLong("stadium_id");
		return new Match(id, startTime, homeTeamName, awayTeamName, leftSeatsCount, stadiumId);
	};
}
