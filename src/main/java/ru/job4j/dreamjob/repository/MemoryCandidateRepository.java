package ru.job4j.dreamjob.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;

import javax.annotation.concurrent.ThreadSafe;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class MemoryCandidateRepository implements CandidateRepository {

    private final AtomicInteger nextId = new AtomicInteger(0);

    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private MemoryCandidateRepository() {
        save(new Candidate(0, "Dmitry Arinin", "Java middle Developer",
                LocalDateTime.now(), 1, 0));
        save(new Candidate(0, "Vasalaty Anton", "Java middle+ Developer",
                LocalDateTime.now(), 2, 0));
        save(new Candidate(0, "Cheban Nikolay", "Java senior Developer",
                LocalDateTime.now(), 3, 0));
        save(new Candidate(0, "Volkova Alina", "Java senior+ Developer",
                LocalDateTime.now(), 1, 0));
    }

    @Override
    public Candidate save(Candidate candidate) {
        candidate.setId(nextId.incrementAndGet());
        candidates.put(candidate.getId(), candidate);
        return candidate;
    }

    @Override
    public boolean deleteById(int id) {
        return candidates.remove(id) != null;
    }

    @Override
    public boolean update(Candidate candidate) {
        return candidates.computeIfPresent(candidate.getId(),
                (id, oldCandidate) -> new Candidate(oldCandidate.getId(),
                        candidate.getName(),
                        candidate.getDescription(),
                        candidate.getCreationDate(),
                        candidate.getCityId(),
                        candidate.getFileId())) != null;
    }

    @Override
    public Optional<Candidate> findById(int id) {
        return Optional.ofNullable(candidates.get(id));
    }

    @Override
    public Collection<Candidate> findAll() {
        return candidates.values();
    }
}
