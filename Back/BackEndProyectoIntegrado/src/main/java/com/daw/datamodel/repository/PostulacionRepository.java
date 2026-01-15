package com.daw.datamodel.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.datamodel.entities.Ciudadano;
import com.daw.datamodel.entities.Oferta;
import com.daw.datamodel.entities.Postulacion;

public interface PostulacionRepository extends JpaRepository<Postulacion, Long>{

	List<Postulacion> findByOfertaIdOferta(Long idOferta);
	
	List<Postulacion> findByOferta(Oferta oferta);
	
	boolean existsByCiudadanoAndOferta(Ciudadano ciudadano, Oferta oferta);

	List<Postulacion> findByCiudadano(Ciudadano ciudadano);

	void deleteByOfertaIdOferta(Long idOferta);



	
}
