package ada.edu.LibraryMid.model.dto;

import ada.edu.LibraryMid.model.entity.CommentEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data

public class CommentModel {
    private String id;
    private String author_name;
    private String content;

    private List<CommentModel> replies;

    public CommentModel(CommentEntity entity) {
        this.id = entity.getId();
        this.author_name = entity.getCommentAuthorName();
        this.content = entity.getCommentContent();
    }
}
