package myapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BookReviews {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String reviewerId;
	private String reviewerName;
	private String reviewerImageUrl;
	private String isbn;
	private String rating;
	private String review;
	private String bookName;
	private String bookImg;
	
	
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookImg() {
		return bookImg;
	}
	public void setBookImg(String bookImg) {
		this.bookImg = bookImg;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReviewerId() {
		return reviewerId;
	}
	public void setReviewerId(String reviewerId) {
		this.reviewerId = reviewerId;
	}
	public String getReviewerName() {
		return reviewerName;
	}
	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}
	public String getReviewerImageUrl() {
		return reviewerImageUrl;
	}
	public void setReviewerImageUrl(String reviewerImageUrl) {
		this.reviewerImageUrl = reviewerImageUrl;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	
	
	
	

}
