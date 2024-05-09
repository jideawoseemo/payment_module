package io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchAccountTransactionResponse {

    private ArrayList<FetchAccountTransaction> fetchAccountTransaction;

}
