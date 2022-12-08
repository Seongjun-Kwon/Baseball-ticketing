package com.programmers.baseballticketing.domain.ticket.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.programmers.baseballticketing.domain.match.model.Match;
import com.programmers.baseballticketing.domain.match.repository.MatchRepository;
import com.programmers.baseballticketing.domain.matchSeat.model.MatchSeat;
import com.programmers.baseballticketing.domain.matchSeat.model.SeatReservationStatus;
import com.programmers.baseballticketing.domain.matchSeat.repository.MatchSeatRepository;
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
	private final MatchRepository matchRepository;
	private final MatchSeatRepository matchSeatRepository;

	public TicketResponseDto createTicket(TicketCreateRequestDto ticketCreateRequestDto) {
		Ticket ticket = ticketCreateRequestDto.toTicket(ticketCreateRequestDto);
		Match match = matchRepository.findById(ticket.getMatchId());
		match.decreaseLeftSeatsCount();
		matchRepository.update(match);

		MatchSeat matchSeat = matchSeatRepository.findByMatchIdAndSeatId(
			ticketCreateRequestDto.getMatchId(),
			ticketCreateRequestDto.getSeatId()
		);
		matchSeat.changeSeatReservationStatus(SeatReservationStatus.RESERVED);
		matchSeatRepository.update(matchSeat);

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
