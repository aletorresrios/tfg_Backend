package com.tfg.tfg_back.controller;

import com.tfg.tfg_back.model.Rol;
import com.tfg.tfg_back.model.Usuario;
import com.tfg.tfg_back.model.UsuarioRol;
import com.tfg.tfg_back.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/")
    public Usuario guardarUsuario (@RequestBody Usuario usuario) throws Exception{

        usuario.setPassword(this.bCryptPasswordEncoder.encode(usuario.getPassword()));

        Set<UsuarioRol> usuarioRoles = new HashSet<>();

        Rol rol = new Rol ();
        rol.setRolId(2L);
        rol.setNombre("NORMAL");

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setRol(rol);
        usuarioRol.setUsuario(usuario);

        usuarioRoles.add(usuarioRol);
        return usuarioService.guardarUsuario(usuario, usuarioRoles);
    }
    /*@GetMapping("/{username}")
    public Usuario obtenerUsuarioUsername(@PathVariable("username") String username){
        return usuarioService.obtenerUsuario(username);
    }*/

    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId){
        usuarioService.eliminarUsuario(usuarioId);
    }
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioService.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id);
        System.out.println(usuario.getUsername());
        return new ResponseEntity<>(usuario, HttpStatus.OK);

    }
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) throws Exception {
        Usuario usuarioActualizado = usuarioService.actualizarUsuario(id, usuario);
        return ResponseEntity.ok(usuarioActualizado);
    }
    @PostMapping("/admin")
    public Usuario guardarAdmin (@RequestBody Usuario usuario) throws Exception{

        usuario.setPassword(this.bCryptPasswordEncoder.encode(usuario.getPassword()));

        Set<UsuarioRol> usuarioRoles = new HashSet<>();

        Rol rol = new Rol ();
        rol.setRolId(1L);
        rol.setNombre("ADMIN");

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setRol(rol);
        usuarioRol.setUsuario(usuario);

        usuarioRoles.add(usuarioRol);
        return usuarioService.guardarUsuario(usuario, usuarioRoles);
    }

}
