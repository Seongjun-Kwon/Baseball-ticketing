package com.programmers.baseballticketing.domain.stadium.repository;

import java.util.Collections;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.programmers.baseballticketing.common.exception.NotFoundException;
import com.programmers.baseballticketing.domain.stadium.model.Seat;
import com.programmers.baseballticketing.domain.stadium.model.SeatSection;

@Repository
public class JdbcSeatRepository implements SeatRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public JdbcSeatRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Seat findById(Long id) {
		Seat seat;
		try {
			seat = jdbcTemplate.queryForObject(
				"select * from seats where id = :id",
				Collections.singletonMap("id", id),
				seatRowMapper);
		} catch (DataAccessException e) {
			throw new NotFoundException();
		}
		return seat;
	}

	RowMapper<Seat> seatRowMapper = (rs, rowNum) -> {
		Long id = rs.getLong("id");
		SeatSection section = SeatSection.getSeatSection(rs.getString("section"));
		Integer seatNumber = rs.getInt("seat_number");
		Integer price = rs.getInt("price");
		Long stadiumId = rs.getLong("stadiumId");
		return new Seat(id, section, seatNumber, price, stadiumId);
	};
}