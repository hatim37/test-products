package com.ecom.testproducts.config;




import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPublicKey;


@ConfigurationProperties(prefix = "rsa")
public record RsakeysConfig(RSAPublicKey publicKey) {
}
