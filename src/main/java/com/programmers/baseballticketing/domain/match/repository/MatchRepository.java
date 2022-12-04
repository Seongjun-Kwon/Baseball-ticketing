package com.programmers.baseballticketing.domain.match.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.programmers.baseballticketing.domain.match.model.Match;
import com.programmers.baseballticketing.domain.stadium.model.Stadium;

public interface MatchRepository {

	Long save(Match match);

	List<Match> findBy(LocalDateTime startTime, Stadium stadium, String team);

	List<Match> findAll();

	void update(Match updatedMatch);

	void deleteById(Long id);

	void deleteAll();
}
