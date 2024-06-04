package quiz.cards.backend.Config;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import quiz.cards.backend.Data.DBWorker;
import quiz.cards.backend.Model.JwtCore;
import quiz.cards.backend.Model.User;

import java.io.IOException;

@Component
public class TokenFilter extends OncePerRequestFilter {

    private JwtCore jwtCore = new JwtCore();
    private DBWorker dbWorker = new DBWorker();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = null;
        Integer userId = null;
        UsernamePasswordAuthenticationToken auth = null;

        try{
            String headerAuth = request.getHeader("Authorization");
            if(headerAuth != null && headerAuth.startsWith("Bearer ")){
                jwt = headerAuth.substring(7);
            }

            if(jwt != null){
                try{
                    userId = Integer.valueOf(jwtCore.getIdFromJwt(jwt));
                } catch (ExpiredJwtException e){

                }
                if(userId != null && SecurityContextHolder.getContext().getAuthentication() == null){
                    User user = dbWorker.getUserById(userId);
                    auth = new UsernamePasswordAuthenticationToken(user, null, null);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        } catch (Exception e){

        }

        filterChain.doFilter(request, response);
    }
}