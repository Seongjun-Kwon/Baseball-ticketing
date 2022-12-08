package com.programmers.baseballticketing.domain.ticket.repository;

import static com.programmers.baseballticketing.domain.ticket.repository.TicketSql.*;

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
import com.programmers.baseballticketing.common.exception.SaveFailException;
import com.programmers.baseballticketing.domain.ticket.model.PaymentStatus;
import com.programmers.baseballticketing.domain.ticket.model.Ticket;

@Repository
public class JdbcTicketRepository implements TicketRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public JdbcTicketRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Long save(Ticket ticket) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		try {
			jdbcTemplate.update(INSERT.getSql(), new MapSqlParameterSource(toTicketParamMap(ticket)), keyHolder);
		} catch (DataAccessException e) {
			throw new SaveFailException();
		}

		return keyHolder.getKey().longValue();
	}

	@Override
	public List<Ticket> findBy(Long matchId, LocalDateTime startTime, String team, Long userId) {
		Map<String, Object> queryMap = new HashMap<>();
		StringBuilder queryBuilder = new StringBuilder("select * from tickets where 1=1");

		if (matchId != null) {
			queryBuilder.append(" and match_id = :matchId");
			queryMap.put("matchId", matchId);
		}
		if (startTime != null) {
			queryBuilder.append(" and start_time = :startTime");
			queryMap.put("startTime", startTime);
		}
		if (team != null) {
			queryBuilder.append(" and team = :team");
			queryMap.put("team", team);
		}
		if (userId != null) {
			queryBuilder.append(" and user_id = :userId");
			queryMap.put("userId", userId);
		}

		return jdbcTemplate.query(queryBuilder.toString(), queryMap, ticketRowMapper);
	}

	@Override
	public List<Ticket> findAll() {
		return jdbcTemplate.query(SELECT_ALL.getSql(), ticketRowMapper);
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

	private Map<String, Object> toTicketParamMap(Ticket ticket) {
		return Map.of(
			"status", ticket.getStatus().name(),
			"createdAt", ticket.getCreatedAt(),
			"lastModifiedAt", ticket.getLastModifiedAt(),
			"price", ticket.getPrice(),
			"userId", ticket.getUserId(),
			"matchId", ticket.getMatchId(),
			"seatId", ticket.getSeatId()
		);
	}

	private RowMapper<Ticket> ticketRowMapper = (rs, rowNum) -> {
		Long id = rs.getLong("id");
		PaymentStatus status = PaymentStatus.getTicketStatus(rs.getString("status"));
		LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
		LocalDateTime lastModifiedAt = rs.getTimestamp("last_modified_at").toLocalDateTime();
		Integer price = rs.getInt("price");
		Long userId = rs.getLong("user_id");
		Long matchId = rs.getLong("match_id");
		Long seatId = rs.getLong("seat_id");
		return new Ticket(id, status, createdAt, lastModifiedAt, price, userId, matchId, seatId);
	};
}
