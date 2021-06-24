package com.magma.sanaa.config;


import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;


public class JwtTokenEnhancer extends JwtAccessTokenConverter {


    public JwtTokenEnhancer(String publicKey, String privateKey) {
        super.setSigningKey(privateKey);
        super.setVerifierKey(publicKey);
    }

}
