package dev.dnnr.api.domain.todo;

import dev.dnnr.api.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Table(name = "todos")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String description;
    private Boolean completed;
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
