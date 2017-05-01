package org.collectiveone.ctwitterapi.repositories;

import java.util.List;

import org.collectiveone.ctwitterapi.model.Account;
import org.collectiveone.ctwitterapi.model.AppUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepositoryIf extends CrudRepository<Account, Long> {
	Account findById(Long id);
	Account findByRequestToken(String requestToken);
	
	@Query("select ac from Account ac where ac.creator.auth0Id = ?1")
	List<Account> findByCreatorAuth0Id(String creatorAuth0Id);
	
	@Query("SELECT mem FROM Account ac JOIN ac.members mem WHERE ac.id = ?1 AND mem.id = ?2")
	AppUser findMember(Long accountId, Long userId);
}
