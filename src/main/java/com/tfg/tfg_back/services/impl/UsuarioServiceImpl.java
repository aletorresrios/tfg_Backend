package com.tfg.tfg_back.services.impl;

import com.tfg.tfg_back.model.Rol;
import com.tfg.tfg_back.model.Usuario;
import com.tfg.tfg_back.model.UsuarioRol;
import com.tfg.tfg_back.repo.RolRepository;
import com.tfg.tfg_back.repo.UsuarioRepository;
import com.tfg.tfg_back.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Override
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
        Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());
        if(usuarioLocal!= null){
            System.out.println("El usuario ya existe");
            throw new Exception("El usuario ya existe");
        }else{
            for (UsuarioRol usuarioRol:usuarioRoles){
                rolRepository.save(usuarioRol.getRol());
            }
            usuario.getUsuarioRoles().addAll(usuarioRoles);
            usuarioLocal = usuarioRepository.save(usuario);
        }
        return usuarioLocal;
    }

    @Override
    public Usuario obtenerUsuario(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public void eliminarUsuario(Long usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario actualizarUsuario(Long id, Usuario usuario) throws Exception {
        Usuario usuarioExistente = usuarioRepository.findById(id).orElseThrow(() -> new Exception("Usuario no encontrado"));

        // Actualiza los campos relevantes del usuario, pero no las authorities
        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setUsername(usuario.getUsername());
        usuarioExistente.setEmail(usuario.getEmail());




        return usuarioRepository.save(usuarioExistente);
    }


}
