package io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.service.interfaces;

import io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.request.*;
import io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.response.*;

import java.io.IOException;
import java.util.List;

public interface PaymentService {

   boolean checkServiceUp(String url) throws IOException;

   DynamicAccountResponse dynamicAccount(DynamicAccountRequest accountRequest);

   StaticAccountResponse staticAccount(StaticAccountRequest staticAccountRequest);

   TransactionQueryResponse getTransactionDetails(TransactionQueryRequest queryRequest);

   TransactionPaymentNotificationResponse testPartnerRequest(TransactionPaymentNotificationRequest notificationRequest);

   DynamicAccountByCodeResponse generateDynamicAccountByCode(DynamicAccountByCodeRequest request);

   FetchAccountTransactionResponse fetchAccount(FetchAccountTransactionRequest transactionRequest);

   List<FetchEachPartnerTransaction> fetchPartner();
}
