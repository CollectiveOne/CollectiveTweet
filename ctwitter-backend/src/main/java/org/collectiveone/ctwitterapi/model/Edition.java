package org.collectiveone.ctwitterapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.collectiveone.ctwitterapi.dtos.EditionDto;

@Entity
public class Edition {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String creatorId;
	@ManyToOne
	private Proposal proposal;
	@ManyToOne
	private Edition parent;
	private String text;
	
	public EditionDto toDto() {
		EditionDto dto = new EditionDto();
		
		dto.setId(id);
		dto.setCreatorId(creatorId);
		if(parent != null) dto.setParentId(parent.getId());
		dto.setProposalId(proposal.getId());
		dto.setText(text);
		dto.setMyvote("neutral");
		
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
	public Proposal getProposal() {
		return proposal;
	}
	public void setProposal(Proposal proposal) {
		this.proposal = proposal;
	}
	public Edition getParent() {
		return parent;
	}
	public void setParent(Edition parent) {
		this.parent = parent;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
		
}
