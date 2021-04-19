package ada.edu.LibraryMid.model.dto;

import ada.edu.LibraryMid.model.entity.BookEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class BookModel implements Serializable {
    private String name;
    private String author;
    private String description;
    private String category;
    private Boolean available;
    private Date published_at;

    private List<CommentModel> comments;

    public BookModel(BookEntity bookEntity) {
    }

    public void setComments(List<CommentModel> comments) {
        this.comments = comments;
    }
}
