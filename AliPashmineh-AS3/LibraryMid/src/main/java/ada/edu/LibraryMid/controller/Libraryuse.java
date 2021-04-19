package ada.edu.LibraryMid.controller;

import ada.edu.LibraryMid.model.dto.CommentModel;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;


public interface Libraryuse {
    ResponseEntity ReturnBook(String name,String email) throws SQLException;
    ResponseEntity TakeBook(String name,String email) throws SQLException;
    ResponseEntity AllBookwithme(String email);
    ResponseEntity WhichBookName(String name);
    ResponseEntity WhichBookCategory(String category);
    ResponseEntity WhichBookAuthor(String author);
    ResponseEntity libhistory(String email);
    ResponseEntity Metalib();
    ResponseEntity GetBookId(int id);
    ResponseEntity CommentOn(CommentModel comment);
    ResponseEntity Replyto(CommentModel reply);
}
