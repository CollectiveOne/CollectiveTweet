package org.collectiveone.ctwitterapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.collectiveone.ctwitterapi.dtos.AppUserDto;

@Entity
public class AppUser {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String auth0Id;
	private String name;
	private String nickname;
	private String email; 
	private String picture;
	
	public AppUserDto toDto() {
		AppUserDto dto = new AppUserDto();
		
		dto.setId(id);
		dto.setAuth0Id(auth0Id);
		dto.setNickname(nickname);
		dto.setEmail(email);
		dto.setPicture(picture);
		
		return dto;
	}
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
}
