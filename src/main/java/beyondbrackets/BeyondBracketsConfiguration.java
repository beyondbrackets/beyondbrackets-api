package beyondbrackets;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BeyondBracketsConfiguration extends Configuration {
    @NotNull
    @Valid
    private DataSourceFactory dataSourceFactory
            = new DataSourceFactory();
    
    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }
}
