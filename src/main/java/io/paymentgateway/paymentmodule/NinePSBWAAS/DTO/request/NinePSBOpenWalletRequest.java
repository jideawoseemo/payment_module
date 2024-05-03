package io.paymentgateway.paymentmodule.NinePSBWAAS.DTO.request;

import lombok.Data;

@Data
public class NinePSBOpenWalletRequest {

    private String transactionTrackingRef;
    private String lastName;
    private String otherNames;
    private String accountName;
    private String phoneNo;
    private String gender;
    private String placeOfBirth;
    private String dateOfBirth;
    private String address;
    private String nationalIdentityNo;
    private String nextOfKinPhoneNo;
    private String referralPhoneNo;
    private String referralName;
    private String otherAccountInformationSource;
    private String email;
    private String customerImage;
    private String customerSignature;
    private String bvn;
    private String notificationPreference;
    private String transactionPermission;
    private String customerId;
    private String walletType;


}
