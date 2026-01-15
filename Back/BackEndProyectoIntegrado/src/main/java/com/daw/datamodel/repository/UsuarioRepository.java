package com.daw.datamodel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.datamodel.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByNombreUsuario(String nombreUsuario);
	 boolean existsByNombreUsuario(String nombreUsuario);
}
