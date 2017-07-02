package beyondbrackets;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import beyondbrackets.api.Post;
import beyondbrackets.db.PostRepository;
import beyondbrackets.resources.PostResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class BeyondBracketsApplication extends Application<BeyondBracketsConfiguration> {

    public static void main(final String[] args) throws Exception {
        new BeyondBracketsApplication().run(args);
    }
    
    private final HibernateBundle<BeyondBracketsConfiguration> hibernate = new HibernateBundle<BeyondBracketsConfiguration>(Post.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(BeyondBracketsConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };
    
    @Override
    public String getName() {
        return "BeyondBrackets";
    }

    @Override
    public void initialize(final Bootstrap<BeyondBracketsConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(final BeyondBracketsConfiguration configuration,
                    final Environment environment) {
        // Enable CORS headers
        final FilterRegistration.Dynamic cors =
            environment.servlets().addFilter("CORS",  CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        
        final PostRepository postRepository = new PostRepository(hibernate.getSessionFactory());
        environment.jersey().register(new PostResource(postRepository));
    	environment.getObjectMapper().registerModule(new JavaTimeModule());
    }

}
