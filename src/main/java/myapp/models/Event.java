package myapp.models;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="publisher_event")
public class Event {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String publisherId;
	private String publisherName;
	private String publisher_imgURL;
	private String event_imgURL;
	private String event_info;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public String getPublisher_imgURL() {
		return publisher_imgURL;
	}
	public void setPublisher_imgURL(String publisher_imgURL) {
		this.publisher_imgURL = publisher_imgURL;
	}
	public String getEvent_imgURL() {
		return event_imgURL;
	}
	public void setEvent_imgURL(String event_imgURL) {
		this.event_imgURL = event_imgURL;
	}
	public String getEvent_info() {
		return event_info;
	}
	public void setEvent_info(String event_info) {
		this.event_info = event_info;
	}

	

}

