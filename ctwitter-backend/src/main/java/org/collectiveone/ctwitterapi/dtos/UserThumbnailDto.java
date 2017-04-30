package org.collectiveone.ctwitterapi.dtos;

public class UserThumbnailDto {
	private String id;
	private String nickname;
	private String picture;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
