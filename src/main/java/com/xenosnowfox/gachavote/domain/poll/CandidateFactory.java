package com.xenosnowfox.gachavote.domain.poll;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@UtilityClass
public class CandidateFactory {

	private final Map<UUID, Candidate> candidates = new HashMap<>();

	public Candidate create(@NonNull final String withLabel) {
		UUID uuid = UUID.randomUUID();
		while (candidates.containsKey(uuid)) {
			uuid = UUID.randomUUID();
		}
		return candidates.computeIfAbsent(uuid, id -> new Candidate(id, withLabel));
	}

	public Candidate fromUuid(@NonNull final UUID withUuid) {
		return candidates.get(withUuid);
	}
}
