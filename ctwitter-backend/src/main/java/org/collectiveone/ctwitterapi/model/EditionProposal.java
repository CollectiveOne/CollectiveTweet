package org.collectiveone.ctwitterapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class EditionProposal {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String creatorId;
	@ManyToOne
	TweetProposal proposal;
	@ManyToOne
	private EditionProposal parent;
	private String text;
	
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
	public TweetProposal getProposal() {
		return proposal;
	}
	public void setProposal(TweetProposal proposal) {
		this.proposal = proposal;
	}
	public EditionProposal getParent() {
		return parent;
	}
	public void setParent(EditionProposal parent) {
		this.parent = parent;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
		
}
