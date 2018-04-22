package edu.mum.cs544.project.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue
	private long id;

	private String name;

	@Column(unique = true)
	private String email;

	private String phone;

	private String password;
	private boolean enabled;

	@Embedded
	private Address address;

	@Transient
	private Integer role;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id") })
	private List<Role> roles = new ArrayList<>();

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "user_skills", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "skill_id", referencedColumnName = "id") })
	private List<Skill> skills = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_project", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
                    @JoinColumn(name = "project_id", referencedColumnName = "id") })
    private List<Project> projects = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();

	public User() {

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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void addRole(Role role) {
		if (!this.roles.contains(role)) {
			this.roles.add(role);
		}
	}

	public void removeRole(Role role) {
		this.roles.remove(role);
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void addSkill(Skill skill) {
		if (!this.skills.contains(skill)) {
			this.skills.add(skill);
		}
	}
	
	public void addComment(Comment comment) {
		if (!this.comments.contains(comment)) {
			this.comments.add(comment);
		}
	}

	public void removeSkills(Skill skill) {
		this.skills.remove(skill);
	}

	public void removeSkills(int skillId) {
		for (Skill skill : skills) {
			if (skill.getId() == skillId) {
				removeSkills(skill);
				break;
			}
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void clearRoles() {
		for (Role role : roles) {
			role.getUsers().clear();
		}
		roles.clear();
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

  public List<Project> getProjects() {
    return projects;
  }

  public void setProjects(List<Project> projects) {
    this.projects = projects;
  }
	
}
