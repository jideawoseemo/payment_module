package io.paymentgateway.paymentmodule.NinePSBWAAS.DTO.request;

import io.paymentgateway.paymentmodule.NinePSBWAAS.utils.Merchant;
import io.paymentgateway.paymentmodule.NinePSBWAAS.utils.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NinePSBSingleWalletRequest {

    private String accountNo;
    private double totalAmount;
    private String transactionId;
    private String narration;
    private Merchant merchant;
    private TransactionType transactionType;

}
