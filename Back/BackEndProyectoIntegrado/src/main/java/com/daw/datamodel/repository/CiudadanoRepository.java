package com.daw.datamodel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.datamodel.entities.Ciudadano;
import com.daw.datamodel.entities.Usuario;

public interface CiudadanoRepository extends JpaRepository<Ciudadano, Long>{

	Optional<Ciudadano> findByUsuario(Usuario usuario);

    List<Ciudadano> findByValidadoFalse();

	Optional<Ciudadano> findByUsuarioNombreUsuario(String nombreUsuario);
	
}
