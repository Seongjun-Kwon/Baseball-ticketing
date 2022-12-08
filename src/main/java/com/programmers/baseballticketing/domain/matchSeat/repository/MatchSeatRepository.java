package com.programmers.baseballticketing.domain.matchSeat.repository;

import java.util.List;

import com.programmers.baseballticketing.domain.matchSeat.model.MatchSeat;
import com.programmers.baseballticketing.domain.matchSeat.model.SeatReservationStatus;

public interface MatchSeatRepository {

	MatchSeat findByMatchIdAndSeatId(Long matchId, Long seatId);

	List<MatchSeat> findAll();

	List<MatchSeat> findBy(Long matchId, SeatReservationStatus status);

	void update(MatchSeat updatedMatchSeat);
}
