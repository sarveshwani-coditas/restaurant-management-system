package com.coditas.restaurantmanagementsystem.dto.user;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class LoginResponse {
    private String accesstoken;
}
