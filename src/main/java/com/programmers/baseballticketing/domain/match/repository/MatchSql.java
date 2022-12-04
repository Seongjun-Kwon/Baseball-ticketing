package com.programmers.baseballticketing.domain.match.repository;

import lombok.Getter;

@Getter
public enum MatchSql {

	INSERT(
		"insert into matches(start_time, home_team_name, away_team_name, left_seats_count, stadium_id) values (:startTime, :homeTeamName, :awayTeamName, :leftSeatsCount, :stadiumId)"),
	SELECT_ALL("select * from matches"),
	UPDATE("update matches set start_time = :startTime where id = :id"),
	DELETE_BY_ID("delete from matches where id = :id"),
	DELETE_ALL("delete from matches");

	private final String sql;

	MatchSql(String sql) {
		this.sql = sql;
	}
}
