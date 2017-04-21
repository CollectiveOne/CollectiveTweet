package org.collectiveone.ctwitterapi.repositories;

import org.collectiveone.ctwitterapi.model.EditionProposal;
import org.springframework.data.repository.CrudRepository;

public interface EditionRepository extends CrudRepository<EditionProposal, Long> {
	
}
