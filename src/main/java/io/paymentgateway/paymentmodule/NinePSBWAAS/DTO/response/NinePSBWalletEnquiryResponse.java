package io.paymentgateway.paymentmodule.NinePSBWAAS.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NinePSBWalletEnquiryResponse {
    private String message;
    private String status;
    private String data;
}
