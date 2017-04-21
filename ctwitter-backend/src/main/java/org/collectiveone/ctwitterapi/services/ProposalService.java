package org.collectiveone.ctwitterapi.services;

import javax.transaction.Transactional;

import org.collectiveone.ctwitterapi.dtos.TweetDto;
import org.collectiveone.ctwitterapi.model.Account;
import org.collectiveone.ctwitterapi.model.EditionProposal;
import org.collectiveone.ctwitterapi.model.TweetProposal;
import org.collectiveone.ctwitterapi.repositories.AccountRepository;
import org.collectiveone.ctwitterapi.repositories.EditionRepository;
import org.collectiveone.ctwitterapi.repositories.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProposalService {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	ProposalRepository proposalRepository;
	
	@Autowired
	EditionRepository editionRepository;
	
	@Transactional
	public String create(String userId, Long accountId, TweetDto tweetDto) {
		TweetProposal proposal = new TweetProposal();
    	EditionProposal edition = new EditionProposal();
    	
    	Account account = accountRepository.findById(accountId);
    	
    	proposal.setCreatorId(userId);
    	proposal.setAccount(account);
    	
    	edition.setCreatorId(userId);
    	edition.setProposal(proposal);
    	edition.setText(tweetDto.getText());
    	
    	proposalRepository.save(proposal);
    	editionRepository.save(edition);
    	
    	return "success";
	}
	
}
