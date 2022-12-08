package com.programmers.baseballticketing.domain.stadium.repository;

import com.programmers.baseballticketing.domain.stadium.model.Seat;

public interface SeatRepository {

	Seat findById(Long id);
}
