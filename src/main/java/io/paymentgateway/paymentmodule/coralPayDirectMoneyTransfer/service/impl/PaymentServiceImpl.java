package io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.service.impl;

import io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.request.*;
import io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.DTO.response.*;
import io.paymentgateway.paymentmodule.coralPayDirectMoneyTransfer.service.interfaces.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final RestClient restClient;

    private final WebClient webClient;

    private final RestTemplate restTemplate;

//    @Value("${CORALPAY.USERNAME}")
    private String username = "b@b.com";

//    @Value("${CORALPAY.PASSWORD}")
    private String password = "3bedf945c074b5d1cbe2b555f0c0cbc2f6c40505dcf2205b9d221edf4c0f98e33316f68f9a0f5227dfc4850aa3bacbf96c1f002f1abf58343ddf98a1852871c3";

    private String accountNumber = "7003268673";

    private String accountName = "Oluwatosin Daniel 2";

    private String transactionAmount = "1000.00";

    private final String referenceNumber = "abc1409432072";

    String credentials = username + ":" + password;
    String base64Credentials =Base64.getEncoder().encodeToString(credentials.getBytes());
    @Override
    public boolean checkServiceUp(String url) throws IOException {
        InetAddress inetAddress = InetAddress.getByName(url);
        return	inetAddress.isReachable(5000);
    }

    @Override
    public DynamicAccountResponse dynamicAccount(DynamicAccountRequest accountRequest) {

        String password = hashValue(username, this.referenceNumber);
        String credentials = username + ":" + password;
        String base64Credentials =Base64.getEncoder().encodeToString(credentials.getBytes());

        System.out.println("  ======================================  "+username);
        System.out.println("   **********************************************  "+password);

        return restClient
                .post()
                .uri("http://sandbox1.coralpay.com:8080/paywithtransfer/moneytransfer/apis/dynamicAccount")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Basic "+base64Credentials)
                .body(accountRequest)
                .retrieve()
                .body(DynamicAccountResponse.class);

    }

    @Override
    public StaticAccountResponse staticAccount(StaticAccountRequest staticAccountRequest) {
        return restClient
                .post()
                .uri("http://sandbox1.coralpay.com:8080/paywithtransfer/moneytransfer/apis/staticAccount")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Basic "+base64Credentials)
                .body(staticAccountRequest)
                .retrieve()
                .body(StaticAccountResponse.class);
    }

    @Override
    public TransactionQueryResponse getTransactionDetails(TransactionQueryRequest queryRequest) {

        String password = hashValue(username, this.referenceNumber);
        String credentials = username + ":" + password;
        String base64Credentials =Base64.getEncoder().encodeToString(credentials.getBytes());
        System.out.println("   **********************************************  "+hashValue(username,this.referenceNumber));
        return restClient.post().
                uri("http://sandbox1.coralpay.com:8080/paywithtransfer/moneytransfer/apis/getTransactionDetails")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization","Basic "+base64Credentials)
                .body(queryRequest)
                .retrieve()
                .body(TransactionQueryResponse.class);

    }

    @Override
    public TransactionPaymentNotificationResponse testPartnerRequest(TransactionPaymentNotificationRequest notificationRequest) {

        String module_value = hashValueForNotification(accountNumber,accountName,transactionAmount);
        String credentials = username + ":" + password;
        String base64Credentials =Base64.getEncoder().encodeToString(credentials.getBytes());

        System.out.println("  ======================================  "+username);
        System.out.println("   **********************************************  "+password);
        System.out.println("   **********************************************  "+module_value);

        return restClient
                .post()
                .uri("http://sandbox1.coralpay.com:8080/paywithtransfer/moneytransfer/apis/testPartnerRequest")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Basic "+base64Credentials)
                .body(notificationRequest)
                .retrieve()
                .body(TransactionPaymentNotificationResponse.class);

    }

    @Override
    public DynamicAccountByCodeResponse generateDynamicAccountByCode(DynamicAccountByCodeRequest request) {

        String password = hashValue(username, "49122280223047");
        String credentials = username + ":" + password;
        String base64Credentials =Base64.getEncoder().encodeToString(credentials.getBytes());

        System.out.println("base64 output------------>  "+ base64Credentials);

        System.out.println("  ======================================  "+username);
        System.out.println("   **********************************************  "+password);
        System.out.println("   **********************************************  "+hashValue(username,referenceNumber));

//                return restClient
//                        .post()
//                        .uri("http://sandbox1.coralpay.com:8080/paywithtransfer/moneytransfer/apis/dynamicAccount")
//                        .contentType(MediaType.APPLICATION_JSON)
//                .header(HttpHeaders.AUTHORIZATION, "Basic "+base64Credentials)
//                .body(request)
//                .retrieve()
//                .body(DynamicAccountByCodeResponse.class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.AUTHORIZATION, "Basic "+base64Credentials);

        HttpEntity<DynamicAccountByCodeRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<DynamicAccountByCodeResponse> dynamicAccountByCodeResponseResponseEntity = restTemplate.postForEntity("http://sandbox1.coralpay.com:8080/paywithtransfer/moneytransfer/apis/dynamicAccount", entity, DynamicAccountByCodeResponse.class);

        log.info("response ----------------->>>" + dynamicAccountByCodeResponseResponseEntity.getBody());
    return dynamicAccountByCodeResponseResponseEntity.getBody();
    }

 //   @Override
//    public FetchAccountTransactionResponse fetchAccount(FetchAccountTransactionRequest transactionRequest) {
//        String password = hashValue(username, "9925204416");
//        String credentials = username + ":" + password;
//        String base64Credentials =Base64.getEncoder().encodeToString(credentials.getBytes());
//
//        FetchAccountTransactionRequest fetchAccountTransactionRequest =  new FetchAccountTransactionRequest();
//
//        fetchAccountTransactionRequest.setAccountNumber("9925204416");
//        fetchAccountTransactionRequest.setFromDate("2023-06-03");
//        fetchAccountTransactionRequest.setToDate("2023-07-17");
//        fetchAccountTransactionRequest.setAuthToken("eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ0ek1WWmtzOG1zcG01TmNHazNFdW1BVjZWYTFRQTVpTlYwcHVfU3hZQldBIn0.eyJleHAiOjE3MTQ1MDEyMTUsImlhdCI6MTcxNDQ5NDAxNSwianRpIjoiMjZlY2U3ZTAtMTM4My00YTRmLTgwNjktNWU3NzU5ZjY2Yjg5IiwiaXNzIjoiaHR0cDovLzEwLjE4NS4yMjMuMjM6ODA4MC9yZWFsbXMvOXBzYiIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiJiOGMyYjczMy1lMzIwLTQyM2EtOGVhNi02ZDBkNTBmNmRlYzEiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJ3YWFzIiwic2Vzc2lvbl9zdGF0ZSI6IjY4Y2VkZWY1LWFlNjUtNDY0Ni05M2U0LTFiZjRkZjg2MWZmYSIsImFjciI6IjEiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsibm90aWZpY2F0aW9uX3JlcXVlcnkiLCJkZWZhdWx0LXJvbGVzLTlwc2IiLCJ3YWxsZXRfdXBncmFkZV9maWxlX3VwbG9hZCIsImNyZWRpdF93YWxsZXQiLCJ3YWxsZXRfcmVxdWVyeSIsIndhbGxldF9zdGF0dXMiLCJkZWJpdF93YWxsZXQiLCJlZGl0X3dhYXNfYWNjIiwid2FsbGV0X3RyYW5zYWN0aW9ucyIsIndhbGxldF9lbnF1aXJ5Iiwib3RoZXJfYmFua3NfZW5xdWlyeSIsImdldF9iYW5rcyIsInVwZ3JhZGVfc3RhdHVzIiwiZ2V0X2FjY291bnRfbnVtYmVyIiwiZ2V0X3JlcXVlc3Rfc3RhdHVzIiwib2ZmbGluZV9hY2Nlc3MiLCJvcGVuX3dhbGxldCIsIndhbGxldF91cGdyYWRlIiwib3Blbl9jb3Jwb3JhdGVfYWNjb3VudCIsInVtYV9hdXRob3JpemF0aW9uIiwiZ2V0X3dhbGxldCIsIm9wZW5fY29ycG9yYXRlX2FjY291bnRfZmlsZV91cGxvYWQiLCJjaGFuZ2Vfd2FsbGV0X3N0YXR1cyIsIndhbGxldF9vdGhlcl9iYW5rcyJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoib3BlbmlkIGVtYWlsIHByb2ZpbGUiLCJzaWQiOiI2OGNlZGVmNS1hZTY1LTQ2NDYtOTNlNC0xYmY0ZGY4NjFmZmEiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsInByZWZlcnJlZF91c2VybmFtZSI6ImthcnJhYm8ifQ.p5J35YcfiGlyOx6HCGasm_h_JttvMlWf8x0mPtd1OcDzVwc0_fCTwaHd6bZy_JS5emnvs9hPnrFUVOdVyUwYIk4o8mFy2sWC_q4pWjY8TqVoRjDZWQnXQZHnemCOKilrZl119nuqm_XnVSa_Z9M6E317bv63jAg9VBZVNSi4RFHzRCw1h3XDM9n0au9vXFhpWQfHP8QLbqwUL0ozNdoPOPN1_p9jXafNpRvfNHd_KoE7PdRZqsg5X3larBDPyMQRLjoVwXps0OkjePLLUXpLqJ38-WkpLSjJmzJkmoMUBUdXvUAJOGF5tLukqGJs3j9iQCh0SMwIrAwGxJTWGQS2OQ");
//
//        return restClient
//                .get()
//                .uri("http://sandbox1.coralpay.com:8080/paywithtransfer/moneytransfer/apis/partners/getAccountTransactions?accountNumber=9925204416&fromDate=2023-06-03&toDate=2023-07-17")
//                .header(HttpHeaders.AUTHORIZATION, "Basic "+base64Credentials)
//                .retrieve()
//                .body(FetchAccountTransactionResponse.class);
//
//    }
    public FetchAccountTransactionResponse fetchAccount(FetchAccountTransactionRequest transactionRequest) {
        String password = hashValue(username, "9925204416");
        String credentials = username + ":" + password;
        String base64Credentials =Base64.getEncoder().encodeToString(credentials.getBytes());

        FetchAccountTransactionRequest fetchAccountTransactionRequest =  new FetchAccountTransactionRequest();

        fetchAccountTransactionRequest.setAccountNumber("9925204416");
        fetchAccountTransactionRequest.setFromDate("2023-06-03");
        fetchAccountTransactionRequest.setToDate("2023-07-17");
        fetchAccountTransactionRequest.setAuthToken("eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ0ek1WWmtzOG1zcG01TmNHazNFdW1BVjZWYTFRQTVpTlYwcHVfU3hZQldBIn0.eyJleHAiOjE3MTQ1MDEyMTUsImlhdCI6MTcxNDQ5NDAxNSwianRpIjoiMjZlY2U3ZTAtMTM4My00YTRmLTgwNjktNWU3NzU5ZjY2Yjg5IiwiaXNzIjoiaHR0cDovLzEwLjE4NS4yMjMuMjM6ODA4MC9yZWFsbXMvOXBzYiIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiJiOGMyYjczMy1lMzIwLTQyM2EtOGVhNi02ZDBkNTBmNmRlYzEiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJ3YWFzIiwic2Vzc2lvbl9zdGF0ZSI6IjY4Y2VkZWY1LWFlNjUtNDY0Ni05M2U0LTFiZjRkZjg2MWZmYSIsImFjciI6IjEiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsibm90aWZpY2F0aW9uX3JlcXVlcnkiLCJkZWZhdWx0LXJvbGVzLTlwc2IiLCJ3YWxsZXRfdXBncmFkZV9maWxlX3VwbG9hZCIsImNyZWRpdF93YWxsZXQiLCJ3YWxsZXRfcmVxdWVyeSIsIndhbGxldF9zdGF0dXMiLCJkZWJpdF93YWxsZXQiLCJlZGl0X3dhYXNfYWNjIiwid2FsbGV0X3RyYW5zYWN0aW9ucyIsIndhbGxldF9lbnF1aXJ5Iiwib3RoZXJfYmFua3NfZW5xdWlyeSIsImdldF9iYW5rcyIsInVwZ3JhZGVfc3RhdHVzIiwiZ2V0X2FjY291bnRfbnVtYmVyIiwiZ2V0X3JlcXVlc3Rfc3RhdHVzIiwib2ZmbGluZV9hY2Nlc3MiLCJvcGVuX3dhbGxldCIsIndhbGxldF91cGdyYWRlIiwib3Blbl9jb3Jwb3JhdGVfYWNjb3VudCIsInVtYV9hdXRob3JpemF0aW9uIiwiZ2V0X3dhbGxldCIsIm9wZW5fY29ycG9yYXRlX2FjY291bnRfZmlsZV91cGxvYWQiLCJjaGFuZ2Vfd2FsbGV0X3N0YXR1cyIsIndhbGxldF9vdGhlcl9iYW5rcyJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoib3BlbmlkIGVtYWlsIHByb2ZpbGUiLCJzaWQiOiI2OGNlZGVmNS1hZTY1LTQ2NDYtOTNlNC0xYmY0ZGY4NjFmZmEiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsInByZWZlcnJlZF91c2VybmFtZSI6ImthcnJhYm8ifQ.p5J35YcfiGlyOx6HCGasm_h_JttvMlWf8x0mPtd1OcDzVwc0_fCTwaHd6bZy_JS5emnvs9hPnrFUVOdVyUwYIk4o8mFy2sWC_q4pWjY8TqVoRjDZWQnXQZHnemCOKilrZl119nuqm_XnVSa_Z9M6E317bv63jAg9VBZVNSi4RFHzRCw1h3XDM9n0au9vXFhpWQfHP8QLbqwUL0ozNdoPOPN1_p9jXafNpRvfNHd_KoE7PdRZqsg5X3larBDPyMQRLjoVwXps0OkjePLLUXpLqJ38-WkpLSjJmzJkmoMUBUdXvUAJOGF5tLukqGJs3j9iQCh0SMwIrAwGxJTWGQS2OQ");

        return restClient
                .post()
                .uri("http://sandbox1.coralpay.com:8080/paywithtransfer/moneytransfer/apis/partners/getAccountTransactions")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Basic "+base64Credentials)
                .body(transactionRequest)
                .retrieve()
                .body(FetchAccountTransactionResponse.class);

    }

    public List<FetchEachPartnerTransaction> fetchPartner() {
        //String password = hashValue(username, this.password);
        String credentials = username + ":" + password;
        String base64Credentials =Base64.getEncoder().encodeToString(credentials.getBytes());

        FetchAccountTransactionRequest fetchAccountTransactionRequest =  new FetchAccountTransactionRequest();

        List<FetchEachPartnerTransaction> responseListz = new ArrayList<FetchEachPartnerTransaction>();


        return restClient
                .get()
                .uri("http://sandbox1.coralpay.com:8080/paywithtransfer/moneytransfer/apis/partners/fetch-partner-transactions")
                .header(HttpHeaders.AUTHORIZATION, "Basic "+base64Credentials)
                .retrieve()
                .body(new ParameterizedTypeReference<List<FetchEachPartnerTransaction>>() {
                });

    }

//    @Override
//    public FetchPartnerTransactionResponse fetchPartner() {
//
//       // String password = hashValue(username, this.password);
//        String credentials = username + ":" + password;
//        String base64Credentials =Base64.getEncoder().encodeToString(credentials.getBytes());
//
//                return restClient
//                .get()
//                .uri("http://sandbox1.coralpay.com:8080/paywithtransfer/moneytransfer/apis/partners/fetch-partner-transactions?clientId=p@p.com&fromDate=2023-06-03&toDate=2023-06-05")
//                .header(HttpHeaders.AUTHORIZATION, "Basic "+base64Credentials)
//                .retrieve()
//                .body(FetchPartnerTransactionResponse.class);
//    }

    public static String hashValue(String username, String referenceNumber) {
        String password1 = referenceNumber+":"+username;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(password1.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch(NoSuchAlgorithmException e) {
            throw new RuntimeException("Hash value not accepted");
        }
        // digest() method is called
        // to calculate message digest of the input string
        // returned as array of byte

    }

    private String hashValueForNotification(String accountNumber, String accountName,String transactionAmount) {
        String password1 = accountNumber+""+accountName+""+transactionAmount;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(password1.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch(NoSuchAlgorithmException e) {
            throw new RuntimeException("Hash value not accepted");
        }
        // digest() method is called
        // to calculate message digest of the input string
        // returned as array of byte

    }

}
