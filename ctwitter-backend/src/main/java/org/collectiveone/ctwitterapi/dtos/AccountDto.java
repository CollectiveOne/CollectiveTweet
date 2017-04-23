package org.collectiveone.ctwitterapi.dtos;

import java.util.ArrayList;
import java.util.List;

public class AccountDto {
	Long id;
	String twitterHandle;
	String creatorId;
	String creatorUsername;
	List<ProposalDto> proposals = new ArrayList<ProposalDto>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTwitterHandle() {
		return twitterHandle;
	}
	public void setTwitterHandle(String twitterHandle) {
		this.twitterHandle = twitterHandle;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreatorUsername() {
		return creatorUsername;
	}
	public void setCreatorUsername(String creatorUsername) {
		this.creatorUsername = creatorUsername;
	}
	public List<ProposalDto> getProposals() {
		return proposals;
	}
	public void setProposals(List<ProposalDto> proposalsDtos) {
		this.proposals = proposalsDtos;
	}
	
}
