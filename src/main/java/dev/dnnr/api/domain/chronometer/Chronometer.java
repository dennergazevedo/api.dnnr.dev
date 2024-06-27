package dev.dnnr.api.domain.chronometer;

import dev.dnnr.api.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Table(name = "chronometers")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chronometer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String description;
    private Date createdAt;
    private Float duration;
    private Boolean interrupted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}