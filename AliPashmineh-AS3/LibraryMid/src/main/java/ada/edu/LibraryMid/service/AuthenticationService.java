package ada.edu.LibraryMid.service;


import ada.edu.LibraryMid.model.dto.RegistrationModel;

public interface AuthenticationService {
    boolean registration(RegistrationModel registrationModel);

    int login(String email, String password);


}
