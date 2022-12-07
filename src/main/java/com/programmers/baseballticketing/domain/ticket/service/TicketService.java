package com.programmers.baseballticketing.domain.ticket.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.programmers.baseballticketing.domain.match.model.Match;
import com.programmers.baseballticketing.domain.ticket.model.Ticket;
import com.programmers.baseballticketing.domain.ticket.repository.TicketRepository;
import com.programmers.baseballticketing.domain.user.model.User;
import com.programmers.baseballticketing.web.domain.ticket.dto.TicketRequestDto;
import com.programmers.baseballticketing.web.domain.ticket.dto.TicketResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketService {

	private final TicketRepository ticketRepository;

	public TicketResponseDto createTicket(TicketRequestDto ticketRequestDto) {
		Ticket ticket = ticketRequestDto.toTicket(ticketRequestDto);
		ticketRepository.save(ticket);
		return TicketResponseDto.toTicketResponseDto(ticket);
	}

	public List<TicketResponseDto> getTicketsBy(Match match, User user) {
		List<Ticket> tickets = ticketRepository.findBy(match, user);
		return tickets.stream()
			.map(TicketResponseDto::toTicketResponseDto)
			.collect(Collectors.toList());
	}

	public List<TicketResponseDto> getAllTickets() {
		final List<Ticket> tickets = ticketRepository.findAll();
		return tickets.stream()
			.map(TicketResponseDto::toTicketResponseDto)
			.collect(Collectors.toList());
	}

	public void deleteTicketById(Long id) {
		ticketRepository.deleteById(id);
	}
}
