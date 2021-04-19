package ada.edu.LibraryMid.service.impl;

import ada.edu.LibraryMid.model.dto.RegistrationModel;
import ada.edu.LibraryMid.model.entity.UserEntity;
import ada.edu.LibraryMid.repository.UserRepository;
import ada.edu.LibraryMid.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    protected static final Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean registration(RegistrationModel registrationModel) {
        try {
            userRepository.save(new UserEntity(registrationModel));
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
    @Override
    public int login(String email, String password) {
        UserEntity user;
        user = userRepository.findFirstByEmail(email);
        if (user != null && user.getId() > 0) {
            user = userRepository.findFirstByEmailAndPassword(email, password);
            if (user != null && user.getId() > 0) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return -1;
        }
    }
}
