package com.programmers.baseballticketing.domain.user.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

	private Long id;
	private String email;
	private final String name;
	private String phoneNumber;
	private final LocalDateTime createdAt;
	private LocalDateTime lastModifiedAt;
}
