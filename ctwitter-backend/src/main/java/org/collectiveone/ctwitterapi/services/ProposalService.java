package org.collectiveone.ctwitterapi.services;

import java.util.List;

import javax.transaction.Transactional;

import org.collectiveone.ctwitterapi.dtos.ProposalDto;
import org.collectiveone.ctwitterapi.dtos.TweetDto;
import org.collectiveone.ctwitterapi.model.Account;
import org.collectiveone.ctwitterapi.model.Edition;
import org.collectiveone.ctwitterapi.model.Proposal;
import org.collectiveone.ctwitterapi.repositories.AccountRepositoryIf;
import org.collectiveone.ctwitterapi.repositories.EditionRepositoryIf;
import org.collectiveone.ctwitterapi.repositories.ProposalRepositoryIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProposalService {
	
	@Autowired
	AccountRepositoryIf accountRepository;
	
	@Autowired
	ProposalRepositoryIf proposalRepository;
	
	@Autowired
	EditionRepositoryIf editionRepository;
	
	@Transactional
	public String create(String userId, Long accountId, TweetDto tweetDto) {
		Proposal proposal = new Proposal();
    	Edition edition = new Edition();
    	
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
	
	@Transactional
	public ProposalDto get(Long proposalId) {
		Proposal proposal = proposalRepository.findById(proposalId);
		List<Edition> editions = editionRepository.findByProposalId(proposal.getId());
		
		ProposalDto proposalDto = proposal.toDto();
		
		for(Edition edition : editions) {
			proposalDto.getEditions().add(edition.toDto());
		}
		
		return proposalDto;
	}
	
	@Transactional
	public String addEdition(String userId, Long proposalId, Long parentId, TweetDto tweetDto) {
		Proposal proposal = proposalRepository.findById(proposalId);

		Edition edition = new Edition();
    	
    	edition.setCreatorId(userId);
    	edition.setProposal(proposal);
    	edition.setText(tweetDto.getText());
    	
    	if(parentId != null) {
    		edition.setParent(editionRepository.findById(parentId));	
    	}
    	
    	editionRepository.save(edition);
    	
    	return "success";
	}
	
}
