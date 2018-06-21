package URLShortPack.Repositories;

import URLShortPack.Model.URLMapping;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyURLRepos extends CrudRepository<URLMapping,String> {
}
