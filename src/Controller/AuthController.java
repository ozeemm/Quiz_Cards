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

        if(repository.checkToken()){
            openMainFrame();
        }

        authFrame.getLoginButton().addActionListener(e->{
            String email = authFrame.getEmail();
            String password = authFrame.getPassword();

            if(repository.authenticate(email, password)){
                openMainFrame();
            }
        });
    }
    private void openMainFrame(){
        authFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        new Controller(repository);
        authFrame.dispose();
    }
}
