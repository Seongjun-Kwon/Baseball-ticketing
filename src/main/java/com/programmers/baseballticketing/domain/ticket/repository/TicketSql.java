package com.programmers.baseballticketing.domain.ticket.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TicketSql {

	INSERT(
		"insert into tickets(status, created_at, last_modified_at, match_id, seat_id, user_id, price) values (:status, :createdAt, :lastModifedAt, :matchId, :seatId, :userId, :price)"),
	SELECT_ALL("select * from tickets"),
	DELETE_BY_ID("delete from tickets where id = :id"),
	DELETE_ALL("delete from tickets");

	private final String sql;
}
