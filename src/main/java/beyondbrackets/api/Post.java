package beyondbrackets.api;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="post")
@NamedQueries(
	    {
	        @NamedQuery(
	            name = "io.beyondbrackets.api.Post.findAll",
	            query = "SELECT p FROM Post p ORDER BY p.date DESC"
	        ),
	        @NamedQuery(
		            name = "io.beyondbrackets.api.Post.findLatest",
		            query = "SELECT p FROM Post p WHERE p.date = (SELECT MAX(m.date) FROM Post m)"
		        )  
	    }
	)
public class Post {
	@JsonProperty
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long id;
	@JsonProperty
	private String url;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate date;
	@JsonProperty
	private String title;
	@JsonProperty
	@Type(type="text")
	private String contents;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
}
