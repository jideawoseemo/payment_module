package io.paymentgateway.paymentmodule.NinePSBWAAS.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NinePSBWalletEnquiryRequest {
    private String accountNo;
}
