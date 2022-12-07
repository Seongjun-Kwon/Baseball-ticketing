package com.programmers.baseballticketing.domain.ticket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MatchSeat {

	private final Long id;
	private SeatReservationStatus status;
	private final Long matchId;
	private final Long seatId;
}
