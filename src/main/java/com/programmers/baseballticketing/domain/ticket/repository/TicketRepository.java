package com.programmers.baseballticketing.domain.ticket.repository;

import java.util.List;

import com.programmers.baseballticketing.domain.match.model.Match;
import com.programmers.baseballticketing.domain.ticket.model.Ticket;
import com.programmers.baseballticketing.domain.user.model.User;

public interface TicketRepository {

	Long save(Ticket ticket);

	List<Ticket> findBy(Match match, User user);

	List<Ticket> findAll();

	void deleteById(Long id);

	void deleteAll();
}
