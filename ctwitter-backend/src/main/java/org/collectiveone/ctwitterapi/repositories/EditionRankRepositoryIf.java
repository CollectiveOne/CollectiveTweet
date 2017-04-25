package org.collectiveone.ctwitterapi.repositories;

import org.collectiveone.ctwitterapi.model.EditionRank;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EditionRankRepositoryIf extends CrudRepository<EditionRank, Long> {
	
	@Query("select er from EditionRank er where er.edition.id = ?1 and er.userId=?2")
	EditionRank findByEditionIdAndUserId(Long editionId, String userId);
	
}
