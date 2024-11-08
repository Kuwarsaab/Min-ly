package Min_ly.demo.repository;


import Min_ly.demo.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {

    Optional<Url> findByUrlCode(String urlCode);
}
