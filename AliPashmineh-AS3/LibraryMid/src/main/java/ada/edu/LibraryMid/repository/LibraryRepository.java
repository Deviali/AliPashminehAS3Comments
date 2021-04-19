package ada.edu.LibraryMid.repository;

import ada.edu.LibraryMid.model.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;

public interface LibraryRepository extends CrudRepository<BookEntity, Long> {
    BookEntity findFirstByName (String name);
    BookEntity findFirstByAuthor (String author);
    BookEntity findFirstByCategory (String category);
    BookEntity findFirstById (int id);
    //BookEntity findFirstByNameAndAvailable (String name);

}
