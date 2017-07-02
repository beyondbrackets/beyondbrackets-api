package beyondbrackets.resources;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beyondbrackets.api.Post;
import beyondbrackets.db.PostRepository;
import io.dropwizard.hibernate.UnitOfWork;

@Path("posts")
@Produces(MediaType.APPLICATION_JSON)
public class PostResource {
	
	private final PostRepository postRepository;
	
	public PostResource(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@GET
	@UnitOfWork
	public List<Post> allPosts() {
		return postRepository.findAll();
	}
	
	@GET
	@UnitOfWork
	@Path("/{url}")
	public Post findOne(@PathParam("url") String url) {
		return postRepository.findByUrl(url);
	}
	
    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Post createPerson(Post post) {
        return postRepository.create(post);
    }
	
}
