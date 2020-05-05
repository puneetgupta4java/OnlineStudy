package com.adda.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
@ApiModel(value = "User", description = "Request Body consists of Data of User")
public class User implements UserDetails, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@ApiModelProperty(value = "The Id or a unique identifier of the user", example = "1")
	private Long id;

	@Column(name = "name")
	@ApiModelProperty(value = "The Id or a unique identifier of the user", required = true, example = "ABC")
	private String name;

	@Column(name = "email")
	@NotNull
	@ApiModelProperty(value = "The Id or a unique identifier of the user", required = true, example = "ABC@gmail.com")
	private String email;

	@Column(name = "password")
	@ApiModelProperty(value = "The Id or a unique identifier of the user", required = true, example = "password")
	private String password;

	@Column(name = "account_state")
	@ApiModelProperty(hidden = true,value = "The accountstate of the user", example = "ACTIVE")
	private String accountState = "ACTIVE"; // Default:Active, Inactive and Deleted

	@ElementCollection(fetch = FetchType.EAGER)
	@ApiModelProperty(hidden = true,value = "The roles of the user", example = "ROLE_USER")
	private List<String> roles = new ArrayList<>();

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<String> getRoles() {
		return roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountState() {
		return accountState;
	}

	public void setAccountState(String accountState) {
		this.accountState = accountState;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", accountState=" + accountState + "]";
	}

	@Override
	@ApiModelProperty(hidden = true,value = "The authorities of the user", example = "USER")
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	@Override
	@ApiModelProperty(hidden = true,value = "The username of the user", example = "adda")
	public String getUsername() {
		return this.name;
	}

	@Override
	@ApiModelProperty(hidden = true,value = "The account not expired of the user", example = "true")
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@ApiModelProperty(hidden = true,value = "The account not locked of the user", example = "true")
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@ApiModelProperty(hidden = true,value = "The account credentials not expired of the user", example = "true")
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@ApiModelProperty(hidden = true,value = "The account is not enabled of the user", example = "true")
	public boolean isEnabled() {
		return true;
	}
}
