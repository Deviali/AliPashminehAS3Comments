package ada.edu.LibraryMid.model.dto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@NoArgsConstructor
@Data
public class RegistrationModel implements Serializable {

    private String firstname;
    private String lastname;
    private String birthday;
    private String email;
    private String password;

    @Override
    public String toString() {
    return "RegistrationModel{" +
            "firstname='" + firstname + '\''+
            ", lastname='" + lastname + '\''+
            ", birthday='" + birthday + '\''+
            ", email='" + email + '\''+
            ", password='" + password + '\''+
            "}";


    }

}
