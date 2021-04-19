package ada.edu.LibraryMid.service.impl;

import ada.edu.LibraryMid.model.dto.CommentModel;
import ada.edu.LibraryMid.model.entity.CommentEntity;
import ada.edu.LibraryMid.repository.CommentRepository;
import ada.edu.LibraryMid.repository.LibraryRepository;
import ada.edu.LibraryMid.service.CommentsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceimpl implements CommentsService {

    protected static final Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private CommentRepository commentRepository;
    private LibraryRepository libraryRepository;

    public CommentServiceimpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<CommentModel> getCommentByBookExtId(long id) {
        List<CommentModel> comments = new ArrayList<>(1);
        Optional<List<CommentEntity>> result = commentRepository.findAllByBookExtId(id);

        if(!result.isPresent()) return comments;

        if(result.get().size() > 0){
            result.get().stream().forEach((commentEntity -> {
                comments.add(new CommentModel(commentEntity));
            }));
        }

        return comments;
    }

    @Override
    public boolean CommentOn(CommentModel commentModel) {
        try{
            commentRepository.save(new CommentEntity(commentModel));
            return true;
        }catch (Exception e) {
              log.error(e.getMessage());
              return false;
         }
    }

    @Override
    public boolean Replyto(CommentModel commentModel) {
        try{
            commentRepository.save(new CommentEntity(commentModel));
            return true;
        }catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
/**
 * public boolean registration(RegistrationModel registrationModel) {
 *         try {
 *             userRepository.save(new UserEntity(registrationModel));
 *             return true;
 *         } catch (Exception e) {
 *             log.error(e.getMessage());
 *             return false;
 *         }db.products.insertOne
 *     }
 * */