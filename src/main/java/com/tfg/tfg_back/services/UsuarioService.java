package com.tfg.tfg_back.services;

import com.tfg.tfg_back.model.Usuario;
import com.tfg.tfg_back.model.UsuarioRol;

import java.util.List;
import java.util.Set;

public interface UsuarioService {

    public Usuario guardarUsuario (Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;

    public Usuario obtenerUsuario (String username);

    public void eliminarUsuario (Long usuarioId);

    public List<Usuario> findAll();

}
