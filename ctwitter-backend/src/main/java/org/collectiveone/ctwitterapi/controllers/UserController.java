package org.collectiveone.ctwitterapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.collectiveone.ctwitterapi.dtos.UserSuggestionDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.client.auth.AuthAPI;
import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.client.mgmt.filter.UserFilter;
import com.auth0.exception.APIException;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.TokenHolder;
import com.auth0.json.mgmt.users.User;
import com.auth0.json.mgmt.users.UsersPage;
import com.auth0.net.AuthRequest;
import com.auth0.net.Request;

@RequestMapping("1/secured/user")
@RestController
public class UserController {
	
	
	@Value("${auth0.issuer}")
    String issuer;
	
	@Value("${auth0.audience}")
    String audience;
	
	@Value("${auth0.secret}")
    String secret;
	
	@RequestMapping(path = "/suggestions",  method = RequestMethod.GET)
    public List<UserSuggestionDto> suggestions(@RequestParam("q") String q) {
		/* Get token */
		AuthAPI auth = new AuthAPI(issuer, audience, secret);
		
		String token = "";
		
		AuthRequest authRequest = auth.requestToken("https://collectiveone.auth0.com/api/v2/");
		try {
		    TokenHolder holder = authRequest.execute();
		    token = holder.getAccessToken();
		} catch (APIException exception) {
		    // api error
		} catch (Auth0Exception exception) {
		    // request error
		}
		
		ManagementAPI mgmt = new ManagementAPI(issuer, token);
		
		UserFilter filter = new UserFilter();
		Request<UsersPage> mgmtRequest = mgmt.users().list(filter);
		
		UsersPage response = null;
		try {
			response = mgmtRequest.execute();
		} catch (APIException exception) {
		    // api error
		} catch (Auth0Exception exception) {
		    // request error
		}
		
		List<UserSuggestionDto> res = new ArrayList<UserSuggestionDto>();
		
		for(User user : response.getItems()) {
			UserSuggestionDto suggestion = new UserSuggestionDto();
			
			suggestion.setId(user.getId());
			suggestion.setPicture(user.getPicture());
			suggestion.setNickname(user.getNickname());
			
			res.add(suggestion);
		}
		
		return res;
    }

}
