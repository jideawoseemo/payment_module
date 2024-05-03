package io.paymentgateway.paymentmodule;


import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;
import java.net.InetAddress;

@SpringBootApplication
public class PaymentModuleApplication  {

	public static void main(String[] args){SpringApplication.run(PaymentModuleApplication.class, args);}

	public boolean run(String url) throws Exception {
		InetAddress inetAddress = InetAddress.getByName(url);
		System.out.print(inetAddress);
	return	inetAddress.isReachable(5000);

	}

	public PaymentModuleApplication() { }

}