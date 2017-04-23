package org.collectiveone.ctwitterapi.repositories;

import java.util.List;

import org.collectiveone.ctwitterapi.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepositoryIf extends CrudRepository<Account, Long> {
	Account findById(Long id);
	Account findByRequestToken(String requestToken);
	List<Account> findByCreatorId(String creatorId);
}
