package management.iconic.iconicmx.security.controller;

import jakarta.validation.Valid;
import management.iconic.iconicmx.security.jwt.JwtProvider;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"*"})
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody @Valid LoginUserDTO loginUserDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().body(buildResponse("Ingrese los datos necesarios", true, null));
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUserDTO.getUsername(), loginUserDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("username", userDetails);
        return ResponseEntity.ok().body(buildResponse("OK", false, data));
    }

    private Map<String, Object> buildResponse(String message, boolean error, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("error", error);
        response.put("data", data);
        return response;
    }
}