package com.curso.springboot.crud.jpa.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	@NotBlank
	@Size(min = 4,max = 12)
	private String username;
	
	@NotBlank
	@Size(min = 8)
	private String password;
	
	private boolean enable;
	
	@ManyToMany/*(cascade= {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER)*/
	@JoinTable(name = "users_roles", 
	   joinColumns = @JoinColumn(name = "user_id"),
	   inverseJoinColumns = @JoinColumn(name = "role_id"),
	   uniqueConstraints = @UniqueConstraint(columnNames = {"user_id,role_id"}))
	private List<Role> roles;
	
	@Transient
	private boolean admin;

	public User() {
		roles = new ArrayList<>();
	}
	
	public User(String username, String password) {
		this();
		this.username = username;
		this.password = password;		
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public boolean isEnabled() {
		return enable;
	}

	public void setEnabled(boolean enable) {
		this.enable = enable;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(enable, id, username, password);
	}
	
	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return enable == other.enable && Objects.equals(id, other.id) && Objects.equals(username, other.username)
				&& Objects.equals(password, other.password);
	}

	@Override
	public String toString() {
		return "{id=" + id + ", username=" + username + ", password=" + password + ", enable=" + enable + ", roles="
				+ roles + "}";
	}
	
	

}
