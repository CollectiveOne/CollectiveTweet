package org.collectiveone.ctwitterapi.dtos;

public class EditionDto {
	private Long id;
	private String creatorId;
	private Long proposalId;
	private Long parentId;
	private String text;
	private String myRankType;
	private Integer myRank;
	
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
	public Long getProposalId() {
		return proposalId;
	}
	public void setProposalId(Long proposalId) {
		this.proposalId = proposalId;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getMyRankType() {
		return myRankType;
	}
	public void setMyRankType(String myRankType) {
		this.myRankType = myRankType;
	}
	public Integer getMyRank() {
		return myRank;
	}
	public void setMyRank(Integer myRank) {
		this.myRank = myRank;
	}
	
}
