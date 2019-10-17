package com.ga.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
	
    @Column(unique = true, nullable = false)
    private String username;
	
    @Column(nullable = false)
    private String password;
    
    @JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinTable(name = "user_songs", joinColumns = {
			@JoinColumn(name = "user_id") }, inverseJoinColumns = @JoinColumn(name = "song_id"))
    private List<Song> songs;
    
    
    
    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "user_role_id", nullable = false)
	private UserRole userRole;
	    
	public UserRole getUserRole() { return userRole; }
	
	public void setUserRole(UserRole userRole) { this.userRole = userRole; }

	
	
    public User() {}
    
    public Long getUserId() {
		return userId;
    }

    public void setUserId(Long userId) {
		this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
		return password;
    }

    public void setPassword(String password) {
		this.password = password;
    }

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
    
    public List<Song> addSong(Song song) {
    	if (this.songs == null) {
    		this.songs = new ArrayList<>();
    	}
    	this.songs.add(song);
    	
    	return this.songs;
    }
    
    public List<Song> removeSong(Long songId) {
    	
    	this.songs = this.songs.stream()
    		.filter(song -> song.getSongId() != songId)
    		.collect(Collectors.toList());
    	
    	return this.songs;
    }
}