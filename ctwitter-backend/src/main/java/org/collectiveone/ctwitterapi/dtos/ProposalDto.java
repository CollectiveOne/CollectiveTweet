package org.collectiveone.ctwitterapi.dtos;

import java.util.ArrayList;
import java.util.List;

public class ProposalDto {
	private Long id;
	private Long creatorId;
	private Long accountId;
	private String firstVersion;
	private List<EditionDto> editions = new ArrayList<EditionDto>();
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getFirstVersion() {
		return firstVersion;
	}
	public void setFirstVersion(String firstVersion) {
		this.firstVersion = firstVersion;
	}
	public List<EditionDto> getEditions() {
		return editions;
	}
	public void setEditions(List<EditionDto> editions) {
		this.editions = editions;
	}
	
	
}
