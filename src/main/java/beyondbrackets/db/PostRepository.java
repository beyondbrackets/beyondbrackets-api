package beyondbrackets.db;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import beyondbrackets.api.Post;
import io.dropwizard.hibernate.AbstractDAO;

public class PostRepository extends AbstractDAO<Post> {

	public PostRepository(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public Post create(Post post) {
        return persist(post);
    }

    public List<Post> findAll() {
        return list(namedQuery("io.beyondbrackets.api.Post.findAll"));
    }

	public Post findByUrl(String url) {
		return uniqueResult(criteria().add(
				Restrictions.eq("url", url)));
	}

}
