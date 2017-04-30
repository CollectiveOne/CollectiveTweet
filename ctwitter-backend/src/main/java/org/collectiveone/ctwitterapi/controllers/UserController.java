package org.collectiveone.ctwitterapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.collectiveone.ctwitterapi.dtos.UserSuggestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.client.mgmt.filter.UserFilter;
import com.auth0.exception.APIException;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.mgmt.users.User;
import com.auth0.json.mgmt.users.UsersPage;
import com.auth0.net.Request;

@RequestMapping("1/secured/user")
@RestController
public class UserController {
	
	@Autowired
	ManagementAPI mgmt;
	
	@RequestMapping(path = "/suggestions",  method = RequestMethod.GET)
    public List<UserSuggestionDto> suggestions(@RequestParam("q") String q) {
		
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
