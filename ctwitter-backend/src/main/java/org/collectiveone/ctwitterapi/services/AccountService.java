package org.collectiveone.ctwitterapi.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.collectiveone.ctwitterapi.dtos.AccountDto;
import org.collectiveone.ctwitterapi.dtos.UserThumbnailDto;
import org.collectiveone.ctwitterapi.model.Account;
import org.collectiveone.ctwitterapi.model.AppUser;
import org.collectiveone.ctwitterapi.repositories.AccountRepositoryIf;
import org.collectiveone.ctwitterapi.repositories.AppUserRepositoryIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.client.mgmt.ManagementAPI;

@Service
public class AccountService {
	
	@Autowired
	ManagementAPI mgmt;
	
	@Autowired
    AccountRepositoryIf accountRepository;
	
	@Autowired
	AppUserRepositoryIf appUserRepository;
	
	@Transactional
	public AccountDto get(Long id) {
		return accountRepository.findById(id).toDto();
	}
	
	@Transactional
	public List<AccountDto> getOfUser(String userAuth0Id) {
		List<Account> accounts = accountRepository.findByCreatorAuth0Id(userAuth0Id);
		
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
		for(AppUser member : account.getMembers()) {
			
			UserThumbnailDto userThumbnail = new UserThumbnailDto();
			userThumbnail.setId(member.getId());
			userThumbnail.setAuth0Id(member.getAuth0Id());
			userThumbnail.setNickname(member.getNickname());
			userThumbnail.setPicture(member.getPicture());
			
			userThumbnails.add(userThumbnail);
			
		}
		
		return userThumbnails;
	}
	
	@Transactional
	public String addMember(Long accountId, Long userId) {
		Account account = accountRepository.findById(accountId);
		AppUser user = appUserRepository.findById(userId);
		
		/* check user is not already a member */
		AppUser member = accountRepository.findMember(accountId, userId);
		if(member == null) {
			account.getMembers().add(user);
			accountRepository.save(account);
			return "success";
		} else {
			return "user already a member";
		}
	}
}
