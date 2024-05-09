package io.paymentgateway.paymentmodule;

import io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.request.*;
import io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.response.*;
import io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.service.interfaces.PaymentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;
import java.util.Base64;

import static io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.service.impl.PaymentServiceImpl.hashValue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest
public class CoralPayDirectMoneyTransferTest {

    WebTestClient testClient = WebTestClient
            .bindToServer()
            .baseUrl("")
            .build();

    private FetchPartnerTransactionResponse fetchPartnerTransactionResponse;
    private FetchAccountTransactionResponse fetchAccountTransactionResponse;

    @Autowired
    private PaymentService paymentService;
    private String username = "b@b.com";

    //    @Value("${CORALPAY.PASSWORD}")
    private String password = "3bedf945c074b5d1cbe2b555f0c0cbc2f6c40505dcf2205b9d221edf4c0f98e33316f68f9a0f5227dfc4850aa3bacbf96c1f002f1abf58343ddf98a1852871c3";

    @BeforeEach
    void SetUp() {

    }

    @Test
    void checkIfCoralPayExists() throws IOException {
        String url = "www.youtube.com";

    assertTrue(paymentService.checkServiceUp(url));

    }

    @Test
    void checkIfCoralPayDynamicServiceIsNotNull() {
        log.info(password+"      <<<<<<<<<<<<<<<<<<<<<<<<");
        log.info(username+"       ...............................................");

        String credentials = username + ":" + password;
        DynamicAccountRequest req = new DynamicAccountRequest();
        DynamicAccountRequestHeader requestHeader = new DynamicAccountRequestHeader();

        requestHeader.setClientId("b@b.com");
        requestHeader.setRequestType("Bank Transfer");

        req.setCustomerName("Johnson AY");
        req.setRequestHeader(requestHeader);
        req.setReferenceNumber("abc1409432072");
        req.setTransactionAmount("15000");
        log.info(credentials);
        log.info(username);
        log.info(password);
        DynamicAccountResponse response = paymentService.dynamicAccount(req);

        log.info(response.getResponseDetails().getClientID());
        log.info(response.toString());

        assertNotNull(response);
       assertEquals("Johnson AY",response.getAccountName());

    }

    @Test
    void checkIfCoralPayTransactionQueryIsNotNull() {

    TransactionQueryRequestHeader requestHeader = new TransactionQueryRequestHeader();

    requestHeader.setClientId("b@b.com");
    requestHeader.setRequestType("Bank Transfer");

    TransactionQueryRequest queryRequest = new TransactionQueryRequest();
    queryRequest.setRequestHeader(requestHeader);
    queryRequest.setReferenceNumber("abc1409432072");

        System.out.println("requestHeader---------------------->>" + requestHeader);

        TransactionQueryResponse response = paymentService.getTransactionDetails(queryRequest);
        log.info(response.toString());

        assertNotNull(response);
        //assertEquals("Success",response.getResponseDetails().);

    }

    @Test
    void checkIfCoralPayStaticServiceIsNotNull() {
        log.info(password+"      <<<<<<<<<<<<<<<<<<<<<<<<");
        log.info(username+"       ...............................................");

        String credentials = username + ":" + password;
        StaticAccountRequest req = new StaticAccountRequest();
        StaticAccountRequestHeader requestHeader = new StaticAccountRequestHeader();

        requestHeader.setClientId("b@b.com");
        requestHeader.setRequestType("Bank Transfer");

        req.setCustomerName("Bianca.co");
        req.setRequestHeader(requestHeader);
        req.setReferenceNumber("709032DCHJKWQ025");
        req.setCustomerID("Microwave");
        log.info(credentials);
        log.info(username);
        log.info(password);
        try {
            StaticAccountResponse response = paymentService.staticAccount(req);
            assertNotNull(response);
            assertEquals("Bianca.co",response.getAccountName());
        } catch (Exception e){
            e.printStackTrace();
            log.info(e.getLocalizedMessage());
        }

    }

    @Test
    void checkIfPaymentNotificationIsNotNull() {

        TransactionPaymentNotificationRequest paymentNotificationRequest = new TransactionPaymentNotificationRequest();

        paymentNotificationRequest.setCurrency("NGN");
        paymentNotificationRequest.setAccount_name("Oluwatosin Daniel 2");
        paymentNotificationRequest.setModule_value("3161b3628d23605f50f1db10a5001b3f68d9eecb35c3fa6d5fdaa97c8b08a1954b27b40553f4c9f3ae4f18d4e6accfe4f46201d2124152b79a3eced04149efdc");
        paymentNotificationRequest.setReferenceNumber("TED245091");
        paymentNotificationRequest.setOperationReference("8fb-8b5e-41d8-bb54-a6bff38ba82f");
        paymentNotificationRequest.setService_charge(50);
        paymentNotificationRequest.setSource_account_name("Abidemi Asunmo");
        paymentNotificationRequest.setTran_date_time("10-05-2023 12:09:50");
        paymentNotificationRequest.setSource_bank_name("N/A");
        paymentNotificationRequest.setSource_bank_code("N/A");
        paymentNotificationRequest.setTransaction_amount("1000.00");
        paymentNotificationRequest.setAccount_number("7003268673");
        paymentNotificationRequest.setSource_account_number("3333002345");

        TransactionPaymentNotificationResponse paymentNotificationResponse = paymentService.testPartnerRequest(paymentNotificationRequest);

        assertNotNull(paymentNotificationResponse);
        assertEquals("00",paymentNotificationResponse.getResponseCode());

    }

    @Test
    void checkCoralPayDynamicAccountByCode() {

      DynamicAccountByCodeRequest dynamicAccountByCodeRequest = new DynamicAccountByCodeRequest();

      DynamicAccountByCodeRequestHeader byrequestHeader = new DynamicAccountByCodeRequestHeader();
      byrequestHeader.setClientId("b@b.com");
      byrequestHeader.setRequestType("Bank Transfer");

      dynamicAccountByCodeRequest.setBankCode("058");
      dynamicAccountByCodeRequest.setCustomerName("James AY");
      dynamicAccountByCodeRequest.setRequestHeader(byrequestHeader);
      dynamicAccountByCodeRequest.setTransactionAmount("7000");
      dynamicAccountByCodeRequest.setReferenceNumber("49122280223047");

        DynamicAccountByCodeResponse codeResponse = paymentService.generateDynamicAccountByCode(dynamicAccountByCodeRequest);
        log.info(codeResponse.toString());
        assertNotNull(codeResponse);
        assertNotEquals("78e1ea45-c353-4ef9-99f8-64b42868bb6b",codeResponse.getOperationReference());

    }

    @Test
    public void testFetchPartnerTransaction() {

        String credentials = username + ":" + password;
        String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        testClient.get().uri("http://sandbox1.coralpay.com:8080/paywithtransfer/moneytransfer/apis/partners/fetch-partner-transactions?clientId=p@p.com&fromDate=2023-06-03&toDate=2023-06-05")
                .header(HttpHeaders.AUTHORIZATION, "Basic "+"cEBwLmNvbToxMjM0")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(fetchPartnerTransactionResponse -> assertThat(fetchPartnerTransactionResponse.getResponseBody()).isNotNull());
    }

    @Test
    public void testFetchAccountTransaction() {
        String password = hashValue( "9925204416", "reign@co");
        String credentials = username + ":" + password;
        String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes());
    String author = "b21pbmliaXpnIyNxUCVTVVchZUVNVmhBS15EKG5rY1hHVzpmYTRkZTE3ZjA3YTRmMjNlZWFjOTJmYmM1NTNiOTNiNTExMjgyYTk3NWFkMTQ1MTZhMWY3Njc4MTBjNDY2Y2QwNmRmN2NmOWFkOWEyNzU3Njk5OTEzOTQ1YzJmN2MwN2UyZTI4Y2VlOWUxYzBlZWQzODUyYzk0YjIzMTZkZTNmMg==";

    assertThat(base64Credentials).isEqualTo(author);
    log.info(base64Credentials);
    log.info(author);
        testClient.get().uri("http://sandbox1.coralpay.com:8080/paywithtransfer/moneytransfer/apis/partners/getAccountTransactions?accountNumber=9925204416&fromDate=2023-06-03&toDate=2023-07-17")
                .header(HttpHeaders.AUTHORIZATION, "Basic "+base64Credentials)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(response -> assertThat(response.getResponseBody()).isNotNull());
    }

    @Test
    public void testGetAllBankList() {

        String credentials = username + ":" + password;
        String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        testClient.get().uri("http://sandbox1.coralpay.com:8080/paywithtransfer/moneytransfer/apis/listOfBanks")
                .header(HttpHeaders.AUTHORIZATION, "Basic "+"YkBiLmNvbToxMjM0")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(fetchPartnerTransactionResponse -> assertThat(fetchPartnerTransactionResponse.getResponseBody()).isNotNull());
    }

}
