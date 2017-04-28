package org.collectiveone.ctwitterapi.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.client.mgmt.ManagementAPI;

//@RequestMapping("1/secured/user")
//@RestController
//public class UserController {
//	
//	@Value("${auth0.domain}")
//    String domain;
//
//	@Value("${auth0.key}")
//    String key;
//	
//	@RequestMapping(path = "/suggestions",  method = RequestMethod.GET)
//    public UserSuggestionsDto suggestions(@RequestParam("q") String q) {
//		ManagementAPI mgmt = new ManagementAPI(domain, key);
//		mgmt.users().list(arg0)
//    }
//
//}
