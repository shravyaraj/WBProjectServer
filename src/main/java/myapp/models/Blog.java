package myapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Blog {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String blogger;
	private String bloggerId;
	private String bloggerImageUrl;
	private String blog;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBlogger() {
		return blogger;
	}
	public void setBlogger(String blogger) {
		this.blogger = blogger;
	}
	public String getBloggerId() {
		return bloggerId;
	}
	public void setBloggerId(String bloggerId) {
		this.bloggerId = bloggerId;
	}
	public String getBloggerImageUrl() {
		return bloggerImageUrl;
	}
	public void setBloggerImageUrl(String bloggerImageUrl) {
		this.bloggerImageUrl = bloggerImageUrl;
	}
	public String getBlog() {
		return blog;
	}
	public void setBlog(String blog) {
		this.blog = blog;
	}

}
