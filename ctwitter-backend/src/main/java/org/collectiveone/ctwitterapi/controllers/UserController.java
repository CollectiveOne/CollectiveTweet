package org.collectiveone.ctwitterapi.controllers;

import java.util.List;

import org.collectiveone.ctwitterapi.dtos.AppUserDto;
import org.collectiveone.ctwitterapi.dtos.UserThumbnailDto;
import org.collectiveone.ctwitterapi.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("1/secured/user")
@RestController
public class UserController {
	
	@Autowired
	AppUserService appUserService;
	
	@RequestMapping(path = "/myProfile",  method = RequestMethod.GET)
    public AppUserDto myProfile() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth.isAuthenticated()){
			return appUserService.getProfile(auth.getName());
		}
		
		return null;
	}
	
	@RequestMapping(path = "/suggestions",  method = RequestMethod.GET)
    public List<UserThumbnailDto> suggestions(@RequestParam("q") String q) {
		
		return appUserService.searchBy(q);
	}

}
