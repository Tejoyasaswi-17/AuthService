package org.example.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import org.example.entities.UserInfo;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
public class UserInfoDTO extends UserInfo {
    private String userName;

    private String lastName;

    private Long phoneNumber;

    private String email;
}
