package com.xenosnowfox.gachavote.domain.poll;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.stream.Stream;

@NoArgsConstructor
public class Poll {

	@Getter
	@Setter
	@NonNull
	private String title = "";

	private final LinkedHashSet<Candidate> candidates = new LinkedHashSet<>();

	public Poll withTitle(@NonNull final String withTitle) {
		this.setTitle(withTitle);
		return this;
	}

	public void resetVoteCounts() {
		candidates.forEach(Candidate::resetVoteCount);
	}

	public int getTotalCandidates() {
		return this.candidates.size();
	}

	public Optional<Candidate> optCandidate(final int withIndex) {
		if (withIndex >= this.getTotalCandidates()) {
			return Optional.empty();
		}

		int i = 0;
		for (Candidate candidate : this.candidates) {
			if (i++ == withIndex) {
				return Optional.of(candidate);
			}
		}

		return Optional.empty();
	}

	public Candidate getCandidate(final int withIndex) {
		return this.optCandidate(withIndex)
				.orElseThrow(IndexOutOfBoundsException::new);
	}

	public Candidate getRandomCandidate() {
		return this.getCandidate(this.getRandomCandidateIndex());
	}

	public Optional<Candidate> optRandomCandidate() {
		return this.optCandidate(this.getRandomCandidateIndex());
	}

	public void addCandidate(@NonNull final Candidate withCandidate) {
		this.candidates.add(withCandidate);
	}

	public void addCandidate(@NonNull final String withLabel) {
		this.addCandidate(CandidateFactory.create(withLabel));
	}

	public Poll withCandidate(@NonNull final Candidate withCandidate) {
		this.addCandidate(withCandidate);
		return this;
	}

	public Poll withCandidate(@NonNull final String withLabel) {
		return this.withCandidate(CandidateFactory.create(withLabel));
	}

	public void removeCandidate(@NonNull final Candidate withCandidate) {
		this.candidates.remove(withCandidate);
	}

	public void removeAllCandidates() {
		this.candidates.clear();
	}

	public Stream<Candidate> streamCandidates() {
		return this.candidates.stream();
	}

	private int getRandomCandidateIndex() {
		return (int) (Math.random() * this.getTotalCandidates());
	}
}
