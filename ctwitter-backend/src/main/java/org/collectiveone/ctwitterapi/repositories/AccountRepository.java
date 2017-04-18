package org.collectiveone.ctwitterapi.repositories;

import org.collectiveone.ctwitterapi.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
	Account findById(Long id);
	Account findByRequestToken(String requestToken);
}
