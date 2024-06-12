package com.tfg.tfg_back.controller;

import com.tfg.tfg_back.model.JwtReponse;
import com.tfg.tfg_back.model.JwtRequest;
import com.tfg.tfg_back.model.Usuario;
import com.tfg.tfg_back.security.JwtUtils;
import com.tfg.tfg_back.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/generate-token")
    public ResponseEntity<?>genrartoken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            autentificar(jwtRequest.getUsername(), jwtRequest.getpassword());
        }catch (Exception exception) {
            exception.printStackTrace();
            throw new Exception("Usuario no encontrado");
        }
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtReponse(token));
    }
    private void autentificar(String username, String password)throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException disabledException){
            throw new Exception("USUARIO DESABILITADO"+ disabledException.getMessage());
        }catch (BadCredentialsException badCredentialsException){
            throw new Exception("CREDENCIALES INVALIDAS"+ badCredentialsException.getMessage());
        }
    }
    @GetMapping("/actual-usuario")
    public Usuario obtenerUsuario(Principal principal){
        return (Usuario) this.userDetailsService.loadUserByUsername(principal.getName());
    }
}
