package org.collectiveone.ctwitterapi.dtos;

import java.util.ArrayList;
import java.util.List;

public class AccountDto {
	Long id;
	String twitterHandle;
	Long creatorId;
	String creatorNickname;
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
	public Long getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreatorNickname() {
		return creatorNickname;
	}
	public void setCreatorNickname(String creatorNickname) {
		this.creatorNickname = creatorNickname;
	}
	public List<ProposalDto> getProposals() {
		return proposals;
	}
	public void setProposals(List<ProposalDto> proposalsDtos) {
		this.proposals = proposalsDtos;
	}
	
}
