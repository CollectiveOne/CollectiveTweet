package org.collectiveone.ctwitterapi.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.collectiveone.ctwitterapi.dtos.AccountDto;
import org.collectiveone.ctwitterapi.model.Account;
import org.collectiveone.ctwitterapi.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
	
	@Autowired
    AccountRepository accountRepository;
	
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
}
