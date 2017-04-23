package org.collectiveone.ctwitterapi.repositories;

import java.util.List;

import org.collectiveone.ctwitterapi.model.Edition;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EditionRepositoryIf extends CrudRepository<Edition, Long> {
	
	Edition findById(Long id);
	
	@Query("select e from Edition e where e.parent = null and e.proposal.id=?1")
	List<Edition> findTopLevelByProposalId(Long proposalId);
	
	@Query("select e from Edition e where e.proposal.id=?1")
	List<Edition> findByProposalId(Long proposalId);
}
