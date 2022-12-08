package com.programmers.baseballticketing.domain.ticket.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.programmers.baseballticketing.domain.ticket.model.Ticket;
import com.programmers.baseballticketing.domain.ticket.repository.TicketRepository;
import com.programmers.baseballticketing.web.domain.ticket.dto.TicketCreateRequestDto;
import com.programmers.baseballticketing.web.domain.ticket.dto.TicketResponseDto;
import com.programmers.baseballticketing.web.domain.ticket.dto.TicketSearchRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketService {

	private final TicketRepository ticketRepository;

	public TicketResponseDto createTicket(TicketCreateRequestDto ticketCreateRequestDto) {
		Ticket ticket = ticketCreateRequestDto.toTicket(ticketCreateRequestDto);
		ticketRepository.save(ticket);
		return TicketResponseDto.toTicketResponseDto(ticket);
	}

	public List<TicketResponseDto> getTicketsBy(TicketSearchRequestDto ticketSearchRequestDto) {
		List<Ticket> tickets = ticketRepository.findBy(
			ticketSearchRequestDto.getMatchId(),
			ticketSearchRequestDto.getStartTime(),
			ticketSearchRequestDto.getTeam(),
			ticketSearchRequestDto.getUserId()
		);
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
