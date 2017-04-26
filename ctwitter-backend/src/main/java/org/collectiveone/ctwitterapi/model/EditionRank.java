package org.collectiveone.ctwitterapi.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class EditionRank {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Edition edition;
	@Enumerated(EnumType.STRING)
	private EditionRankType rankType;
	private int rank;
	private String userId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Edition getEdition() {
		return edition;
	}
	public void setEdition(Edition edition) {
		this.edition = edition;
	}
	public EditionRankType getRankType() {
		return rankType;
	}
	public void setRankType(EditionRankType rankType) {
		this.rankType = rankType;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
