package io.paymentgateway.paymentmodule.NinePSBWAAS.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NinePSBAuthenticateResponse {

    private String accessToken;
    private String message;
    private String refreshToken;
    private String expiresIn;
    private String refreshExpiresIn;

}
