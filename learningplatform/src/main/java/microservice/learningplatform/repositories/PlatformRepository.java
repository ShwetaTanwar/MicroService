package microservice.learningplatform.repositories;
import microservice.learningplatform.entities.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlatformRepository extends JpaRepository<Platform,String> {
    //Optional<Platform> findByName(String name);


    List<Platform> findByNameContaining(String name);

    List<Platform> findByIdContaining(String id);
}
