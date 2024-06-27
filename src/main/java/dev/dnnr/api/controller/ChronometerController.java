package dev.dnnr.api.controller;

import dev.dnnr.api.domain.chronometer.Chronometer;
import dev.dnnr.api.domain.chronometer.ChronometerCreateDTO;
import dev.dnnr.api.domain.chronometer.ChronometerRequestDTO;
import dev.dnnr.api.domain.user.User;
import dev.dnnr.api.service.ChronometerService;
import dev.dnnr.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tools/chronometer")
public class ChronometerController {

    @Autowired
    private ChronometerService chronometerService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Chronometer> create(@RequestHeader("Authorization") String token, @RequestBody @Valid ChronometerRequestDTO body) {
        User user = userService.getAuthenticatedUser(token);
        var newChronometerDTO = new ChronometerCreateDTO(body.description(), body.duration(), body.interrupted(), user.getId());
        Chronometer newChronometer = this.chronometerService.createChronometer(newChronometerDTO);
        return ResponseEntity.ok(newChronometer);
    }

    @GetMapping
    public ResponseEntity<Optional<List<Chronometer>>> getAllByUser(@RequestHeader("Authorization") String token) {
        User user = userService.getAuthenticatedUser(token);
        Optional<List<Chronometer>> chronometerList = this.chronometerService.getChronometerByUserId(user.getId());
        return ResponseEntity.ok(chronometerList);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam @Valid UUID chronometer_id) {
        this.chronometerService.deleteChronometer(chronometer_id);
        return ResponseEntity.ok(null);
    }
}