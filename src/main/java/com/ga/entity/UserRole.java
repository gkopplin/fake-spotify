package com.ga.entity;

import java.util.List;


import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    @Column(unique = true, nullable = false)
    private String name;
    
    
    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "user_role_id", nullable = false)
	private UserRole userRole;
	    
	public UserRole getUserRole() { return userRole; }
	
	public void setUserRole(UserRole userRole) { this.userRole = userRole; }
    

    public UserRole() {}

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}