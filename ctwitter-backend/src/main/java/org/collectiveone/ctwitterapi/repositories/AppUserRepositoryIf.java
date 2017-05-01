package org.collectiveone.ctwitterapi.repositories;

import java.util.List;

import org.collectiveone.ctwitterapi.model.AppUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepositoryIf extends CrudRepository<AppUser, Long> {
	AppUser findById(Long id);
	AppUser findByAuth0Id(String auth0Id);
	@Query("select us from AppUser us where us.nickname like %?1%")
	List<AppUser> searchBy(String q);
}
