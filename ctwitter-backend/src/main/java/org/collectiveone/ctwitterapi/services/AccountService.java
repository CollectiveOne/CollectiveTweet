package org.collectiveone.ctwitterapi.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.collectiveone.ctwitterapi.dtos.AccountDto;
import org.collectiveone.ctwitterapi.dtos.UserThumbnailDto;
import org.collectiveone.ctwitterapi.model.Account;
import org.collectiveone.ctwitterapi.repositories.AccountRepositoryIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.mgmt.users.User;

@Service
public class AccountService {
	
	@Autowired
	ManagementAPI mgmt;
	
	@Autowired
    AccountRepositoryIf accountRepository;
	
	@Transactional
	public AccountDto get(Long id) {
		return accountRepository.findById(id).toDto();
	}
	
	@Transactional
	public List<AccountDto> getOfUser(String userid) {
		List<Account> accounts = accountRepository.findByCreatorId(userid);
		
		List<AccountDto> accountsDtos = new ArrayList<AccountDto>();
		for(Account account : accounts) {
			accountsDtos.add(account.toDto());
		}
		
		return accountsDtos;
	}
	
	@Transactional
	public List<UserThumbnailDto> getMembers(Long accountId) {
		Account account = accountRepository.findById(accountId);
		
		List<UserThumbnailDto> userThumbnails = new ArrayList<UserThumbnailDto>();
		for(String memberId : account.getMembersIds()) {
			
			User user = null;
			try {
				user = mgmt.users().get(memberId, null).execute();
				
				UserThumbnailDto userThumbnail = new UserThumbnailDto();
				userThumbnail.setId(user.getId());
				userThumbnail.setNickname(user.getNickname());
				userThumbnail.setPicture(user.getPicture());
				
				userThumbnails.add(userThumbnail);
				
			} catch (Auth0Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return userThumbnails;
	}
}
