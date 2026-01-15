package com.daw.datamodel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.daw.datamodel.entities.Empresa;
import com.daw.datamodel.entities.Usuario;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

	Optional<Empresa> findByUsuario(Usuario usuario);
    List<Empresa> findByValidadoFalse();
    @Query("SELECT e FROM Empresa e WHERE e.usuario.nombreUsuario = :nombreUsuario")
    Optional<Empresa> findByUsuarioNombreUsuario(@Param("nombreUsuario") String nombreUsuario);
	
	
}
