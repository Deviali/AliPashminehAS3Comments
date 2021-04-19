package ada.edu.LibraryMid.service.impl;

import ada.edu.LibraryMid.model.dto.BookModel;
import ada.edu.LibraryMid.model.entity.BookEntity;
import ada.edu.LibraryMid.model.entity.HistoryEntity;
import ada.edu.LibraryMid.model.entity.UserEntity;
import ada.edu.LibraryMid.repository.CurrentRepository;
import ada.edu.LibraryMid.repository.HistoryRepository;
import ada.edu.LibraryMid.repository.LibraryRepository;
import ada.edu.LibraryMid.repository.UserRepository;
import ada.edu.LibraryMid.service.CommentsService;
import ada.edu.LibraryMid.service.libService;
import ada.edu.LibraryMid.util.DBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.Formatter;
import java.util.Optional;

@Service
public class libSeviceimpl implements libService {

    protected static final Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LibraryRepository libraryRepository;
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private CurrentRepository currentRepository;
    @Autowired
    private CommentsService commentsService;

    private Connection conn;

    @Override
    public int ReturnBook(String name, String email) throws SQLException {
        UserEntity user;
        BookEntity book;
        user = userRepository.findFirstByEmail(email);
        if (user != null && user.getId() > 0) {
        book = libraryRepository.findFirstByName(name);
        if(book != null){
            if (book != null && book.getId() > 0 && !book.getAvailable()) {
                conn= DBUtils.getConnection();
                PreparedStatement preparedStatement = null;
                PreparedStatement pst2= null;
                String query = "UPDATE booklib SET available = 'true'  WHERE name = ?";
                String query2 = "DELETE FROM Current_Takers WHERE nameofbook = ? ";
                try {

                    preparedStatement = conn.prepareStatement(query);
                    pst2 = conn.prepareStatement(query2);

                    preparedStatement.setString(1,name);
                    pst2.setString(1,name);

                    preparedStatement.executeUpdate();
                    pst2.executeUpdate();

                    return 1;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }return 0;
        }return -2;

        }
        return -1;
    }

    @Override
    public int TakeBook(String name, String email) throws SQLException {
        UserEntity user;
        BookEntity book;
        user = userRepository.findFirstByEmail(email);
        if (user != null && user.getId() > 0) {
            book = libraryRepository.findFirstByName(name);
            if (book != null && book.getAvailable()) {
                conn= DBUtils.getConnection();
                PreparedStatement preparedStatement= null;
                PreparedStatement pst2= null;
                PreparedStatement pst3= null;
                String query = "UPDATE booklib SET available = 'false' WHERE name = ? ";
                String query2 = "INSERT INTO Current_Takers(nameofbook, useremail) VALUES (?, ?)";
                String query3 = "INSERT INTO Library_History(nameofbook, useremail) VALUES (?, ?)";
                try {
                    preparedStatement = conn.prepareStatement(query);
                    pst2 = conn.prepareStatement(query2);
                    pst3 = conn.prepareStatement(query3);

                    preparedStatement.setString(1,name);
                    pst2.setString(1,name);
                    pst2.setString(2,email);
                    pst3.setString(1,name);
                    pst3.setString(2,email);
                    preparedStatement.executeUpdate();
                    pst2.executeUpdate();
                    pst3.executeUpdate();
                    return 1;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            return 0;
        }
        return -1;
    }

    @Override
    public String AllBookwithme(String email) {
        conn= DBUtils.getConnection();
        PreparedStatement preparedStatement= null;
        String query = "SELECT nameofbook FROM current_takers where useremail = ?";
        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,email);
             ResultSet rs = preparedStatement.executeQuery();
            String result = "";
            while (rs.next()) {
                 result += rs.getString(1) + ", ";
            }
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "fail";
    }

    @Override
    public String WhichBookName(String name) {
        BookEntity book;
        book = libraryRepository.findFirstByName(name);
        if (book != null) {
        conn= DBUtils.getConnection();
        PreparedStatement preparedStatement= null;
        String query = "SELECT * FROM booklib where name = ?";
        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,name);
            ResultSet rs = preparedStatement.executeQuery();
            String result = "The book you are searching for: Title of the book: ";
            while (rs.next()) {
                result += rs.getString(2) + ",Author is ";
                result += rs.getString(3) + ",description: ";
                result += rs.getString(4) + ",category is ";
                result += rs.getString(5) + ",availability is ";
                result += rs.getString(6) + ",Published at ";
                result += rs.getString(7) + ".";
            }
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "fail";
    }else return "The book you are looking for does not exist in library";
    }

    @Override
    public String WhichBookCategory(String category) {
        BookEntity book;
        book = libraryRepository.findFirstByCategory(category);
        if (book != null) {
            conn= DBUtils.getConnection();
            PreparedStatement preparedStatement= null;
            String query = "SELECT * FROM booklib where category = ?";
            try {
                preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1,category);
                ResultSet rs = preparedStatement.executeQuery();
                String result = "Following books are in the genre you are looking for: ";
                while (rs.next()) {
                    result = "Title of the book: ";
                    result += rs.getString(2) + ",Author is ";
                    result += rs.getString(3) + ",description: ";
                    result += rs.getString(4) + ",category is ";
                    result += rs.getString(5) + ",availability is ";
                    result += rs.getString(6) + ",Published at ";
                    result += rs.getString(7) + ". ";
                }
                return result;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return "fail";
        }else return "The category you are looking for does not exist in library";
    }

    @Override
    public String WhichBookAuthor(String author) {
        BookEntity book;
        book = libraryRepository.findFirstByAuthor(author);
        if (book != null) {
            conn= DBUtils.getConnection();
            PreparedStatement preparedStatement= null;
            String query = "SELECT * FROM booklib where author = ?";
            try {
                preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1,author);
                ResultSet rs = preparedStatement.executeQuery();
                String result = "Following books are written by the author you are looking for: ";
                while (rs.next()) {
                    result = "Title of the book: ";
                    result += rs.getString(2) + ",Author is ";
                    result += rs.getString(3) + ",description: ";
                    result += rs.getString(4) + ",category is ";
                    result += rs.getString(5) + ",availability is ";
                    result += rs.getString(6) + ",Published at ";
                    result += rs.getString(7) + ". ";
                }
                return result;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return "fail";
        }else return "The author you are looking for does not exist in library";
    }

    @Override
    public String libhistory(String email) {
        HistoryEntity user;
        user = historyRepository.findFirstByUsermail(email);
        if (user != null){
        conn= DBUtils.getConnection();
        PreparedStatement preparedStatement= null;
        String query = "SELECT * FROM library_history where useremail = ?";
        try {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,email);
            ResultSet rs = preparedStatement.executeQuery();
            String result = "Here are all the books you have previously taken: ";
            while (rs.next()) {
                result += rs.getString(1) + ", ";
            }
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "failure at code";
    }else return "You have not taken anybooks previously";
    }


    @Override
    public void Metalib() {
        conn= DBUtils.getConnection();
        PreparedStatement preparedStatement= null;
        String query = "SELECT * FROM booklib ";
        try {
            preparedStatement = conn.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

                ResultSetMetaData meta = rs.getMetaData();

                String colname1 = meta.getColumnName(1);
                String colname2 = meta.getColumnName(2);
                String colname3 = meta.getColumnName(3);
                String colname4 = meta.getColumnName(4);
                String colname5 = meta.getColumnName(5);
                String colname6 = meta.getColumnName(6);
                String colname7 = meta.getColumnName(7);

                Formatter fmt1 = new Formatter();
                fmt1.format("%-31s%s", colname1, colname2);
                System.out.print(fmt1+"                              ");
                Formatter fmt8 = new Formatter();
                fmt8.format("%-21s%s", colname3, colname4);
                System.out.print(fmt8+"                  ");
                Formatter fmt9 = new Formatter();
                fmt9.format("%-21s%s", colname5, colname6);
                System.out.print(fmt9+"                  ");
                Formatter fmt10 = new Formatter();
                fmt10.format("%-21s%s", colname7, "");
                System.out.println(fmt10+" ");

                while (rs.next()) {

                    Formatter fmt2 = new Formatter();
                    fmt2.format("%-21s", rs.getString(1));
                    System.out.print(fmt2+"         ");
                    Formatter fmt3 = new Formatter();
                    fmt3.format("%-21s",rs.getString(2));
                    System.out.print(fmt3+"         ");
                    Formatter fmt4 = new Formatter();
                    fmt4.format("%-21s",rs.getString(3));
                    System.out.print(fmt4+"         ");
                    Formatter fmt5 = new Formatter();
                    fmt5.format("%-21s",rs.getString(4));
                    System.out.print(fmt5+"         ");
                    Formatter fmt6 = new Formatter();
                    fmt6.format("%-21s",rs.getString(5));
                    System.out.print(fmt6+"         ");
                    Formatter fmt7 = new Formatter();
                    fmt7.format("%-21s",rs.getString(6));
                    System.out.print(fmt7);
                    System.out.println(rs.getString(7));
                }
            PreparedStatement pst2= null;
            String query2 = "SELECT * FROM current_takers ";
            try {
                pst2 = conn.prepareStatement(query2);
                ResultSet rs2 = pst2.executeQuery();

                ResultSetMetaData meta2 = rs2.getMetaData();

                String colname11 = meta2.getColumnName(1);
                String colname12 = meta2.getColumnName(2);
                Formatter fmt12 = new Formatter();
                fmt12.format("%-21s%s", colname11, colname12);
                System.out.print(fmt12);
                while (rs2.next()) {

                    Formatter fmt11 = new Formatter();
                    fmt11.format("%-21s", rs2.getString(1));
                    System.out.print(fmt11+"         ");
                    Formatter fmt13 = new Formatter();
                    fmt13.format("%-21s",rs2.getString(2));
                    System.out.print(fmt13+"         ");
                    Formatter fmt14 = new Formatter();
                    fmt14.format("%-21s",rs2.getString(3));
                    System.out.print(fmt14+"         ");
        }

    } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public BookModel GetBookId(int id) {
        Optional<BookEntity> book = Optional.ofNullable(libraryRepository.findFirstById(id));
        if (!book.isPresent()) {
            try {
                throw new SQLException();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        BookModel bookModel = new BookModel(book.get());
        bookModel.setComments(commentsService.getCommentByBookExtId(id));
        return bookModel;
    }
        }



