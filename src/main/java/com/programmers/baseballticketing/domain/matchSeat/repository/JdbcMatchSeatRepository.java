package com.programmers.baseballticketing.domain.matchSeat.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.programmers.baseballticketing.common.exception.NotFoundException;
import com.programmers.baseballticketing.common.exception.UpdateFailException;
import com.programmers.baseballticketing.domain.matchSeat.model.MatchSeat;
import com.programmers.baseballticketing.domain.matchSeat.model.SeatReservationStatus;

@Repository
public class JdbcMatchSeatRepository implements MatchSeatRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public JdbcMatchSeatRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public MatchSeat findByMatchIdAndSeatId(Long matchId, Long seatId) {
		MatchSeat matchSeat;
		try {
			matchSeat = jdbcTemplate.queryForObject(
				"select * from matchSeats where match_id = :matchId and seat_id = :seatId",
				Map.of("matchId", matchId, "seatId", seatId),
				matchSeatRowMapper);
		} catch (DataAccessException e) {
			throw new NotFoundException();
		}
		return matchSeat;
	}

	@Override
	public List<MatchSeat> findAll() {
		return jdbcTemplate.query("select * from match_seats", matchSeatRowMapper);
	}

	@Override
	public List<MatchSeat> findBy(Long matchId, SeatReservationStatus status) {
		Map<String, Object> queryMap = new HashMap<>();
		StringBuilder queryBuilder = new StringBuilder("select * from match_seats where 1=1");

		if (matchId != null) {
			queryBuilder.append(" and match_id = :matchId");
			queryMap.put("matchId", matchId);
		}
		if (status != null) {
			queryBuilder.append(" and status = :status");
			queryMap.put("status", status.name());
		}

		return jdbcTemplate.query(queryBuilder.toString(), queryMap, matchSeatRowMapper);
	}

	@Override
	public void update(MatchSeat updatedMatchSeat) {
		try {
			jdbcTemplate.update(
				"update match_seats set status = :status where id = :id",
				toMatchSeatParamMap(updatedMatchSeat)
			);
		} catch (DataAccessException e) {
			throw new UpdateFailException();
		}
	}

	private Map<String, Object> toMatchSeatParamMap(MatchSeat matchSeat) {
		return Map.of(
			"id", matchSeat.getId(),
			"status", matchSeat.getStatus().name(),
			"matchId", matchSeat.getMatchId(),
			"seatId", matchSeat.getSeatId()
		);
	}

	private RowMapper<MatchSeat> matchSeatRowMapper = (rs, rowNum) -> {
		Long id = rs.getLong("id");
		SeatReservationStatus status = SeatReservationStatus.getSeatReservationStatus(rs.getString("status"));
		Long matchId = rs.getLong("match_id");
		Long seatId = rs.getLong("seat_id");
		return new MatchSeat(id, status, matchId, seatId);
	};
}
