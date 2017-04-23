package org.collectiveone.ctwitterapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.collectiveone.ctwitterapi.dtos.ProposalDto;

@Entity
public class Proposal {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String creatorId;
	@ManyToOne
	private Account account;
	
	public ProposalDto toDto() {
		ProposalDto dto = new ProposalDto();
		
		dto.setId(id);
		dto.setCreatorId(creatorId);
		dto.setAccountId(account.getId());
		
		return dto;
	}
	
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
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
}
