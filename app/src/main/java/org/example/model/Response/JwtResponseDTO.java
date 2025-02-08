package org.example.model.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.N;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponseDTO {

    private String accessToken;
    private String token;
}
