package com.programmers.baseballticketing.domain.matchSeat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class MatchSeat {

	private Long id;
	private SeatReservationStatus status;
	private final Long matchId;
	private final Long seatId;
}
