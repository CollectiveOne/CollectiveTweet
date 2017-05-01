package org.collectiveone.ctwitterapi.dtos;

public class AppUserDto {
	private Long id;
	private String auth0Id;
	private String email;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public void setPicture(String picture) {
		this.picture = picture;
	}
}
