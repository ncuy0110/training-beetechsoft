package com.beetech.mvcspringboot.controller.publics.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String accessToken;
}
