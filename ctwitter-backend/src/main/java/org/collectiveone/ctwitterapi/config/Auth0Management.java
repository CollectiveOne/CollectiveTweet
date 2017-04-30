package org.collectiveone.ctwitterapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.auth0.client.auth.AuthAPI;
import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.exception.APIException;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.TokenHolder;
import com.auth0.net.AuthRequest;

@Component
public class Auth0Management {
	
	@Value("${auth0.issuer}")
    String issuer;
	
	@Value("${auth0.audience}")
    String audience;
	
	@Value("${auth0.secret}")
    String secret;
	
	@Autowired
	Environment env;
	
	@Autowired
	AuthAPI authAPI; 
	
	@Autowired
	ManagementAPI mgmt; 
	
	@Bean
	public AuthAPI authAPI() {
		return new AuthAPI(issuer, audience, secret);
	}
	
	@Bean
	public ManagementAPI managementAPI() {
		String token = "";
		
		AuthRequest authRequest = authAPI.requestToken("https://collectiveone.auth0.com/api/v2/");
		try {
		    TokenHolder holder = authRequest.execute();
		    token = holder.getAccessToken();
		    /* TODO: configure token update logi 
		    String refreshToken = holder.getRefreshToken(); */
		} catch (APIException exception) {
		    // api error
		} catch (Auth0Exception exception) {
		    // request error
		}
		
		
		return new ManagementAPI(issuer, token);
	}
	
}
