package myapp.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import myapp.models.BookReviews;
import myapp.models.Topic;
import myapp.repositories.BookReviewRepository;




@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class BookReviewService {
	
	@Autowired
	BookReviewRepository reviewRepository;
	
	@GetMapping("/api/reviews")
	public Iterable<BookReviews>findAllReviews(){
		return reviewRepository.findAll();
		
	}
	
	@PostMapping("/api/review/{isbn}")
	public BookReviews createReview(@PathVariable("isbn") String isbn, @RequestBody BookReviews newReview) {
		
		System.out.println("inside create Book review");
		System.out.println(newReview.getReview());
		
			BookReviews reviews = reviewRepository.findByReviewerIdandIsbn(newReview.getReviewerId(), newReview.getIsbn());
			if(reviews!=null)
			{
				newReview.setId(reviews.getId());
				if(newReview.getIsbn() == null)
				{
					newReview.setIsbn(reviews.getIsbn());
				}
				if(newReview.getReviewerName() == null)
				{
					newReview.setReviewerName(reviews.getReviewerName());
				}
				if(newReview.getReviewerImageUrl() == null)
				{
					newReview.setReviewerImageUrl(reviews.getReviewerImageUrl());
				}
				if(newReview.getRating() == null)
				{
					newReview.setRating(reviews.getRating());
				}
				if(newReview.getReviewerId() == null)
				{
					newReview.setReviewerId(reviews.getReviewerId());
				}
				if(newReview.getReview() == null)
				{
					newReview.setReview(reviews.getReview());
				}
				
			}
	
			return reviewRepository.save(newReview);
				
	}
	
	@GetMapping("/api/review/{isbn}")
	public List<BookReviews> fetchAllReviewsForABook(@PathVariable("isbn") String isbn)
	{
		List<BookReviews> reviewLst = reviewRepository.findReviewsOfABook(isbn);
		return reviewLst; 
	}
	
	@GetMapping("/api/reviewedBooks/{userId}")
	public List<BookReviews> fetchAllReviewsForReviewer(@PathVariable("userId") String userId)
	{
		List<BookReviews> reviewLst = reviewRepository.findReviewsOfReviewer(userId);
		return reviewLst; 
	}
	

}
