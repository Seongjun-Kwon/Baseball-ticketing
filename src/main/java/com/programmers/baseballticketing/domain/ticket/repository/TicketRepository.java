package com.programmers.baseballticketing.domain.ticket.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.programmers.baseballticketing.domain.ticket.model.Ticket;

public interface TicketRepository {

	Long save(Ticket ticket);

	List<Ticket> findBy(Long matchId, LocalDateTime startTime, String team, Long userId);

	List<Ticket> findAll();

	void deleteById(Long id);

	void deleteAll();
}
