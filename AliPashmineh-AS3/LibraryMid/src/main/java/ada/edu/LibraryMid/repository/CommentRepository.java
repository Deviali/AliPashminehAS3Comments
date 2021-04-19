package ada.edu.LibraryMid.repository;

import ada.edu.LibraryMid.model.entity.CommentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends MongoRepository<CommentEntity, String> {
    Optional<List<CommentEntity>> findAllByBookExtId(Long id);

}
