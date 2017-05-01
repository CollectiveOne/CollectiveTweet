package org.collectiveone.ctwitterapi.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.collectiveone.ctwitterapi.dtos.AppUserDto;
import org.collectiveone.ctwitterapi.dtos.UserThumbnailDto;
import org.collectiveone.ctwitterapi.model.AppUser;
import org.collectiveone.ctwitterapi.repositories.AppUserRepositoryIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.exception.APIException;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.mgmt.users.User;

@Service
public class AppUserService {
	
	@Autowired
	AppUserRepositoryIf appUserRepository;
	
	@Autowired
	ManagementAPI mgmt;
	
	@Transactional
	public AppUser get(String auth0Id) {
		
		AppUser appUser = appUserRepository.findByAuth0Id(auth0Id);
		
		if(appUser == null) {
			appUser = addUserToLocalDB(auth0Id);
		}
    	
    	return appUser;
	}
	
	@Transactional
	public List<UserThumbnailDto> searchBy(String q) {
		List<AppUser> users = appUserRepository.searchBy(q);
		
		List<UserThumbnailDto> usersThumbnails = new ArrayList<UserThumbnailDto>();
		for(AppUser user : users) {
		
			UserThumbnailDto thumbnail = new UserThumbnailDto();
			
			thumbnail.setId(user.getId());
			thumbnail.setAuth0Id(user.getAuth0Id());
			thumbnail.setPicture(user.getPicture());
			thumbnail.setNickname(user.getNickname());
			
			usersThumbnails.add(thumbnail);
		}
		
		return usersThumbnails;
		
	}
	
	@Transactional
	public AppUserDto getProfile(String auth0Id) {
		AppUser appUser = appUserRepository.findByAuth0Id(auth0Id);
		
		if(appUser == null) {
			appUser = addUserToLocalDB(auth0Id);
		}
		
		return appUser.toDto();
	}
	
	private AppUser addUserToLocalDB(String auth0Id) {
		/* retrieve from Auth0 */
		AppUser appUser = null;
		User auth0User = null;
		try {
			auth0User = mgmt.users().get(auth0Id, null).execute();
			
			appUser = new AppUser();
			
			appUser.setAuth0Id(auth0User.getId());
			appUser.setName(auth0User.getName());
			appUser.setNickname(auth0User.getNickname());
			appUser.setEmail(auth0User.getEmail());
			appUser.setPicture(auth0User.getPicture());
			
			appUser = appUserRepository.save(appUser);
			
		} catch (APIException exception) {
		    // api error
		} catch (Auth0Exception exception) {
		    // request error
		}
		
		return appUser;
	} 
	
}
