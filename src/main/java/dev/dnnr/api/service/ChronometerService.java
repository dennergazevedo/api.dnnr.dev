package dev.dnnr.api.service;

import dev.dnnr.api.domain.chronometer.Chronometer;
import dev.dnnr.api.domain.chronometer.ChronometerCreateDTO;
import dev.dnnr.api.domain.user.User;
import dev.dnnr.api.repositories.ChronometerRepository;
import dev.dnnr.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChronometerService {

    @Autowired
    private ChronometerRepository chronometerRepositoryy;
    @Autowired
    private UserRepository userRepository;

    public Chronometer createChronometer(ChronometerCreateDTO data) {
        Optional<User> user = this.userRepository.findById(data.user_id());

        Chronometer newChronometer = new Chronometer();
        Timestamp createdAt = Timestamp.from(Instant.now());

        if (user.isPresent()) {
            newChronometer.setUser(user.get());
            newChronometer.setCreatedAt(createdAt);
            newChronometer.setDescription(data.description());
            newChronometer.setDuration(data.duration());
            newChronometer.setInterrupted(data.interrupted());

            chronometerRepositoryy.save(newChronometer);
            return newChronometer;
        } else return null;
    }

    public Optional<List<Chronometer>> getChronometerByUserId(UUID user_id) {
        return Optional.ofNullable(this.chronometerRepositoryy.findByUserId(user_id));
    }

    public void deleteChronometer(UUID chronometer_id) {
        this.chronometerRepositoryy.deleteById(chronometer_id);
    }
}
