package com.xenosnowfox.gachavote.domain.poll;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

/**
 * Represents a possible option that can be voted for within a poll.
 */
public class Candidate implements Comparable<Candidate> {

	@Getter
	@NonNull
	private final UUID uuid;

	@Getter
	private String label;

	@Getter
	int voteCount;

	Candidate(@NonNull final UUID withUuid, @NonNull final String withLabel) {
		this.setLabel(withLabel);
		this.resetVoteCount();
		this.uuid = withUuid;
	}

	private void setLabel(@NonNull final String withLabel) {
		if (withLabel.isBlank()) {
			throw new IllegalArgumentException("Label cannot be blank or empty.");
		}
		this.label = withLabel.trim();
	}

	public void resetVoteCount() {
		this.voteCount = 0;
	}

	public void incrementVoteCount() {
		this.incrementVoteCount(1);
	}

	public void incrementVoteCount(final int withTotalVotes) {
		this.voteCount += withTotalVotes;
	}

	@Override
	public int compareTo(final Candidate o) {
		return Integer.compare(o.getVoteCount(), this.getVoteCount());
	}
}
