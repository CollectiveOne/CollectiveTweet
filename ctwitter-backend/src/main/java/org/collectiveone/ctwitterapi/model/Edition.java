package org.collectiveone.ctwitterapi.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	@OneToMany(mappedBy="edition")
	private List<EditionRank> ranks;
	
	public EditionDto toDto() {
		EditionDto dto = new EditionDto();
		
		dto.setId(id);
		dto.setCreatorId(creatorId);
		if(parent != null) dto.setParentId(parent.getId());
		dto.setProposalId(proposal.getId());
		dto.setText(text);
		
		/* these are set outside if the user is specified */
		dto.setMyRankType(EditionRankType.NOTRANKED.toString());
		dto.setMyRank(0);
		
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

	public List<EditionRank> getRanks() {
		return ranks;
	}

	public void setRanks(List<EditionRank> ranks) {
		this.ranks = ranks;
	}
	
		
}
