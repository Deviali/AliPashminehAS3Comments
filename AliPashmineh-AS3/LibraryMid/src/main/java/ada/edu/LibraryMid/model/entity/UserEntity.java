package ada.edu.LibraryMid.model.entity;


import ada.edu.LibraryMid.model.dto.RegistrationModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String birthday;
    private String password;
    private String email;

    public UserEntity(RegistrationModel registrationModel) {
        this.firstname = registrationModel.getFirstname();
        this.lastname = registrationModel.getLastname();
        this.birthday = registrationModel.getBirthday();
        this.password = registrationModel.getPassword();
        this.email = registrationModel.getEmail();
    }
}
