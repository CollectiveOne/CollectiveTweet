package org.collectiveone.ctwitterapi.services;

import java.util.List;

import javax.transaction.Transactional;

import org.collectiveone.ctwitterapi.dtos.EditionDto;
import org.collectiveone.ctwitterapi.dtos.ProposalDto;
import org.collectiveone.ctwitterapi.dtos.TweetDto;
import org.collectiveone.ctwitterapi.model.Account;
import org.collectiveone.ctwitterapi.model.Edition;
import org.collectiveone.ctwitterapi.model.EditionRank;
import org.collectiveone.ctwitterapi.model.Proposal;
import org.collectiveone.ctwitterapi.repositories.AccountRepositoryIf;
import org.collectiveone.ctwitterapi.repositories.EditionRankRepositoryIf;
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
	
	@Autowired
	EditionRankRepositoryIf editionRankRepository;
	
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
	public ProposalDto get(Long proposalId, String userId) {
		Proposal proposal = proposalRepository.findById(proposalId);
		List<Edition> editions = editionRepository.findByProposalId(proposal.getId());
		
		ProposalDto proposalDto = proposal.toDto();
		
		for(Edition edition : editions) {
			EditionDto editionDto = edition.toDto();
			
			if(userId != null) {
				/* add rank info if userId is not null */
				EditionRank myrank = editionRankRepository.findByEditionIdAndUserId(edition.getId(), userId);
				editionDto.setMyRank(myrank.getRank());
			}
			
			proposalDto.getEditions().add(editionDto);
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
	
	@Transactional
	public String rankEdition(String userId, Long proposalId, Long editionId, int rank) {
		
		EditionRank myrank = editionRankRepository.findByEditionIdAndUserId(editionId, userId);
    	
    	if(myrank == null) {
    		/* create new rank if not found */
    		myrank = new EditionRank();
    		myrank.setEdition(editionRepository.findById(editionId));
    		myrank.setUserId(userId);
    	}
    	
    	myrank.setRank(rank);
    	editionRankRepository.save(myrank);
    	
    	return "success";
	}
	
}
