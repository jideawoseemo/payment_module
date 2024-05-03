package io.paymentgateway.paymentmodule.NinePSBWAAS.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NinePSBAuthenticateRequest {

    private String username;

    private String password;

    private String clientId;

    private String clientSecret;

}
