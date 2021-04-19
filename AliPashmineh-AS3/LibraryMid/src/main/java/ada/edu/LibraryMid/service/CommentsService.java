package ada.edu.LibraryMid.service;

import ada.edu.LibraryMid.model.dto.CommentModel;

import java.util.List;

public interface CommentsService {
    List<CommentModel>   getCommentByBookExtId(long id);
    boolean CommentOn(CommentModel commentModel);
    boolean Replyto(CommentModel commentModel);

}
