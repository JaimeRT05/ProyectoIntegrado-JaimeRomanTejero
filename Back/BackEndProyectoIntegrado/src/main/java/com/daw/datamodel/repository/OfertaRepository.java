package com.daw.datamodel.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.daw.datamodel.entities.Empresa;
import com.daw.datamodel.entities.EstadoOferta;
import com.daw.datamodel.entities.Oferta;

public interface OfertaRepository extends JpaRepository<Oferta, Long>{

	Long countByEmpresa(Empresa empresa);
	
	List<Oferta> findByEmpresaIdEmpresa(Long idEmpresa);
	
	List<Oferta> findByEmpresaAndEstadoAndFechaPublicacionBefore(Empresa empresa, EstadoOferta estado, LocalDate fecha);
	
	@Query("""
		       SELECT o FROM Oferta o
		       WHERE o.estado = 'ABIERTA'
		       AND (:tipo IS NULL OR o.tipoOferta = :tipo)
		       AND (:palabraClave IS NULL OR LOWER(o.perfilProfesional) LIKE LOWER(CONCAT('%', :palabraClave, '%')))
		       """)
		List<Oferta> listarOfertasPublicas( @Param("tipo") String tipo, @Param("palabraClave") String palabraClave);
	
	@Query("""
		    SELECT o FROM Oferta o
		    WHERE o.empresa = :empresa
		      AND o.estado = 'ABIERTA'
		      AND o.fechaPublicacion <= :fechaLimite
		      AND SIZE(o.postulaciones) = 0
		""")
		List<Oferta> ofertasPendientesVerificar(@Param("empresa") Empresa empresa, @Param("fechaLimite") LocalDate fechaLimite);



}
