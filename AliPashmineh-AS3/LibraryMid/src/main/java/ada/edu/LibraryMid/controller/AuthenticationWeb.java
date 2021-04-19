package ada.edu.LibraryMid.controller;

import ada.edu.LibraryMid.model.dto.RegistrationModel;
import  org.springframework.http.ResponseEntity;

public interface AuthenticationWeb {
 Object login(String email, String password);
 ResponseEntity register(RegistrationModel formData);
}
