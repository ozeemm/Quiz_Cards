package quiz.cards.backend.Controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import quiz.cards.backend.Config.JwtCore;
import quiz.cards.backend.Requests.LoginRequest;
import quiz.cards.backend.Model.User;

@RestController
@RequestMapping("/api/user")
public class UsersController extends AbstractDataController {
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private JwtCore jwtCore = new JwtCore();

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        User user = getDbWorker().getUserByEmail(loginRequest.getEmail());
        if(user == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if(passwordEncoder.matches(loginRequest.getPassword() ,user.getPassword())){
            String jwt = jwtCore.generateToken(user);
            System.out.println(user.getEmail() + " logged in");
            return ResponseEntity.ok(jwt);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody LoginRequest loginRequest){
        if(getDbWorker().isUserExists(loginRequest.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
        }

        User user = new User();
        user.setEmail(loginRequest.getEmail());
        String hashedPassword = passwordEncoder.encode(loginRequest.getPassword());
        user.setPassword(hashedPassword);

        getDbWorker().createUser(user);
        System.out.println("New user registered: " + user.getEmail());
        return ResponseEntity.ok("User created");
    }

    @PostMapping("/login/check")
    public ResponseEntity<?> checkAuth(){
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/data")
    public String getUserData(){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("Got UserData for " + user.getEmail());
        return user.getUserdata();
    }

    @PostMapping("/data")
    public void updateUserData(@RequestBody String userData){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setUserdata(userData);
        getDbWorker().updateUserData(user);
        System.out.println("Updated UserData for " + user.getEmail());
    }
}
