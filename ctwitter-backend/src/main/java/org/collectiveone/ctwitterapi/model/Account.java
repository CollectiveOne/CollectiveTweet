package org.collectiveone.ctwitterapi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.collectiveone.ctwitterapi.dtos.AccountDto;

@Entity
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	private AccountState state;
	@ManyToOne
	private AppUser creator;
	private String twitterHandle;
	
	@OneToMany(mappedBy="account")
	private List<Proposal> proposals = new ArrayList<Proposal>();
	
	@ManyToMany(cascade=CascadeType.ALL)
	private List<AppUser> members = new ArrayList<AppUser>();
	
	private String requestToken;
	private String requestTokenSecret;
	private String accessToken;
	private String accessTokenSecret;
	
	public AccountDto toDto() {
		AccountDto dto = new AccountDto();
		
		dto.setId(id);
		dto.setTwitterHandle(twitterHandle);
		dto.setCreatorId(creator.getId());
		dto.setCreatorNickname(creator.getNickname());
		
		if(proposals != null) {
			for(Proposal proposal : proposals) {
				dto.getProposals().add(proposal.toDto());
			}
		}
		
		return dto;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public AccountState getState() {
		return state;
	}
	public void setState(AccountState state) {
		this.state = state;
	}
	public AppUser getCreator() {
		return creator;
	}
	public void setCreator(AppUser creator) {
		this.creator = creator;
	}
	public String getTwitterHandle() {
		return twitterHandle;
	}
	public void setTwitterHandle(String twitterHandle) {
		this.twitterHandle = twitterHandle;
	}
	public List<Proposal> getProposals() {
		return proposals;
	}
	public void setProposals(List<Proposal> proposals) {
		this.proposals = proposals;
	}
	public List<AppUser> getMembers() {
		return members;
	}
	public void setMembers(List<AppUser> members) {
		this.members = members;
	}

	public String getRequestToken() {
		return requestToken;
	}
	public void setRequestToken(String requestToken) {
		this.requestToken = requestToken;
	}
	public String getRequestTokenSecret() {
		return requestTokenSecret;
	}
	public void setRequestTokenSecret(String requestTokenSecret) {
		this.requestTokenSecret = requestTokenSecret;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}
	public void setAccessTokenSecret(String accessTokenSecret) {
		this.accessTokenSecret = accessTokenSecret;
	}
	
}
