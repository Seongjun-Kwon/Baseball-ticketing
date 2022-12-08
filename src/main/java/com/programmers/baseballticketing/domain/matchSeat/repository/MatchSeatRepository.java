package com.programmers.baseballticketing.domain.matchSeat.repository;

import java.util.List;

import com.programmers.baseballticketing.domain.matchSeat.model.MatchSeat;
import com.programmers.baseballticketing.domain.matchSeat.model.SeatReservationStatus;

public interface MatchSeatRepository {

	List<MatchSeat> findAll();

	List<MatchSeat> findBy(Long matchId, SeatReservationStatus status);
}
