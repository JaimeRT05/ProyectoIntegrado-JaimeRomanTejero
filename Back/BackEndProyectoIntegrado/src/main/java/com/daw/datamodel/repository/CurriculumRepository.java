package com.daw.datamodel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.datamodel.entities.Ciudadano;
import com.daw.datamodel.entities.Curriculum;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {

    List<Curriculum> findByCiudadano(Ciudadano ciudadano);
}
