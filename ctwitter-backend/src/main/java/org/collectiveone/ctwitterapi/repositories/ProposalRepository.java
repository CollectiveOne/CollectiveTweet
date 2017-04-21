package org.collectiveone.ctwitterapi.repositories;

import org.collectiveone.ctwitterapi.model.TweetProposal;
import org.springframework.data.repository.CrudRepository;

public interface ProposalRepository extends CrudRepository<TweetProposal, Long> {
	
}
