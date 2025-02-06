package org.example.entities;


import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.*;


@Entity
@NoArgsConstructor
@Data
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@AllArgsConstructor
@Table(name = "tokens")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String token;

    private Instant expiryDate;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "user_id")
    private UserInfo userInfo;
}
