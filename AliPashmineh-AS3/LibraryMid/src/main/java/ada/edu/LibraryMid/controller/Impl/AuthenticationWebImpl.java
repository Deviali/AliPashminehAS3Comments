package ada.edu.LibraryMid.controller.Impl;

import ada.edu.LibraryMid.controller.AuthenticationWeb;
import ada.edu.LibraryMid.model.dto.RegistrationModel;
import ada.edu.LibraryMid.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationWebImpl implements AuthenticationWeb {


    @Autowired
    private AuthenticationService authenticationService;


    @Override
    @RequestMapping(value ="/login", method = RequestMethod.GET)
    public ResponseEntity login(
            @RequestHeader("email") String email,
            @RequestHeader("password") String password) {
      int result = authenticationService.login(email, password);
      if (result < 0){
          return ResponseEntity.notFound().build();
      } else if (result == 0){
          return new ResponseEntity(HttpStatus.FORBIDDEN);
      }else {
          return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT); //Tea is good :D
      }
    }
    @Override
    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResponseEntity register(
            @RequestBody RegistrationModel formData) {
        if(authenticationService.registration((formData))){
            return ResponseEntity.created( null).build();
        } else {
            return  ResponseEntity.unprocessableEntity().build();
        }

    }
}
