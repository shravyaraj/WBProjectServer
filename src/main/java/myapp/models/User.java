package myapp.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table (name="User")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String gender;
	private String email;
	private String role;
	private String bio;
	private String coverPic; 
	private ArrayList<String> category = new ArrayList<String>();
	@JoinTable(name = "Following", joinColumns = {
			@JoinColumn(name = "User", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
					@JoinColumn(name = "following", referencedColumnName = "id", nullable = false)})
	@ManyToMany
	@JsonIgnore
	private Collection<User>followingCollection;
	
	@JoinTable(name = "Follower", joinColumns = {
			@JoinColumn (name = "User", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
					@JoinColumn(name = "follower", referencedColumnName = "id", nullable = false)})
	@ManyToMany
	@JsonIgnore
	private Collection<User>followerCollection;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT")
	private Date dateOfBirth;
	
	
	
	
	
	
	public Collection<User> getFollowingCollection() {
		return followingCollection;
	}
	public void setFollowingCollection(Collection<User> followingCollection) {
		this.followingCollection = followingCollection;
	}
	public Collection<User> getFollowerCollection() {
		return followerCollection;
	}
	public void setFollowerCollection(Collection<User> followerCollection) {
		this.followerCollection = followerCollection;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String phone) {
		this.gender = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public ArrayList<String> getCategory() {
		return category;
	}
	public void setCategory(ArrayList<String> category) {
		this.category = category;
	}
	public String getCoverPic() {
		return coverPic;
	}
	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}
}
