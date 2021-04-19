package ada.edu.LibraryMid.repository;

import ada.edu.LibraryMid.model.entity.HistoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface HistoryRepository extends CrudRepository <HistoryEntity,Long> {
    HistoryEntity findFirstByUsermail(String email);
}
