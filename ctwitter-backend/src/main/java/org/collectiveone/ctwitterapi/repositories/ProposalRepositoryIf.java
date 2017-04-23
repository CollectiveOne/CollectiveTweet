package org.collectiveone.ctwitterapi.repositories;

import org.collectiveone.ctwitterapi.model.Proposal;
import org.springframework.data.repository.CrudRepository;

public interface ProposalRepositoryIf extends CrudRepository<Proposal, Long> {
	Proposal findById(Long id);
}
