package com.programmers.baseballticketing.web.domain.ticket.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programmers.baseballticketing.domain.ticket.service.TicketService;
import com.programmers.baseballticketing.web.domain.ticket.dto.TicketCreateRequestDto;
import com.programmers.baseballticketing.web.domain.ticket.dto.TicketResponseDto;
import com.programmers.baseballticketing.web.domain.ticket.dto.TicketSearchRequestDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/ticket")
@RequiredArgsConstructor
public class TicketApiController {

	private final TicketService ticketService;

	@PostMapping
	public TicketResponseDto createTicket(@RequestBody TicketCreateRequestDto ticketCreateRequestDto) {
		return ticketService.createTicket(ticketCreateRequestDto);
	}

	@GetMapping("/all")
	public List<TicketResponseDto> getTickets(@ModelAttribute TicketSearchRequestDto ticketSearchRequestDto) {
		return ticketService.getTicketsBy(ticketSearchRequestDto);
	}

	@DeleteMapping("/{ticketId}")
	public ResponseEntity deleteTicket(@PathVariable Long id) {
		ticketService.deleteTicketById(id);
		return ResponseEntity.ok().build();
	}
}
