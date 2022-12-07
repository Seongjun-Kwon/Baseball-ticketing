package com.programmers.baseballticketing.domain.user.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {

	private final Long id;
	private final String name;
	private String email;
	private String phoneNumber;
	private final LocalDateTime createdAt;
	private LocalDateTime lastModifiedAt;
}
