package com.beetech.mvcspringboot.controller.publics.dto;

import lombok.*;

/**
 * The type Authentication response.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    /**
     * accessToken String
     */
    private String accessToken;
}
