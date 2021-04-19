package ada.edu.LibraryMid.model.entity;

import ada.edu.LibraryMid.model.dto.CommentModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@Entity
@Data
@Document(collection = ("Comments"))
    public class CommentEntity {

     @Id
    private String id;

    private String bookExtId;

    private String commentAuthorName;

    private String commentContent;

    @OneToMany(mappedBy = "replies")
    private List<CommentEntity> replies;


    public CommentEntity(CommentModel commentModel) {
    }
}
