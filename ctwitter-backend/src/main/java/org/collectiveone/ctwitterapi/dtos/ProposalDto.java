package org.collectiveone.ctwitterapi.dtos;

import java.util.ArrayList;
import java.util.List;

public class ProposalDto {
	private Long id;
	private String creatorId;
	private Long accountId;
	private List<EditionDto> editions = new ArrayList<EditionDto>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public List<EditionDto> getEditions() {
		return editions;
	}
	public void setEditions(List<EditionDto> editions) {
		this.editions = editions;
	}
	
	
}
