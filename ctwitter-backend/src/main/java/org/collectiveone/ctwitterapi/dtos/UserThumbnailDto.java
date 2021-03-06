package org.collectiveone.ctwitterapi.dtos;

public class UserThumbnailDto {
	private Long id;
	private String auth0Id;
	private String nickname;
	private String picture;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAuth0Id() {
		return auth0Id;
	}
	public void setAuth0Id(String auth0Id) {
		this.auth0Id = auth0Id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String profilePicUrl) {
		this.picture = profilePicUrl;
	}
}
