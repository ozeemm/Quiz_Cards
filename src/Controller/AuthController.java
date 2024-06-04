package Controller;

import Data.Repository;
import View.AuthFrame;

import javax.swing.*;

public class AuthController {
    private Repository repository;
    private AuthFrame authFrame;
    public void start(){
        repository = new Repository();
        authFrame = new AuthFrame();

        authFrame.getLoginButton().addActionListener(e->{
            String email = authFrame.getEmail();
            String password = authFrame.getPassword();

            if(repository.authenticate(email, password)){
                authFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                new Controller(repository);
                authFrame.dispose();
            }
        });
    }
}
