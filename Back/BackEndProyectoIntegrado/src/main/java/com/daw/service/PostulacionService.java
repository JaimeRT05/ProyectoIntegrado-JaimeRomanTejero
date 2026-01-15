package com.daw.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.datamodel.dto.CandidatoDTO;
import com.daw.datamodel.dto.EmpresaDTO;
import com.daw.datamodel.dto.PostulacionDTO;
import com.daw.datamodel.entities.Ciudadano;
import com.daw.datamodel.entities.Curriculum;
import com.daw.datamodel.entities.EstadoPostulacion;
import com.daw.datamodel.entities.Oferta;
import com.daw.datamodel.entities.Postulacion;
import com.daw.datamodel.repository.CiudadanoRepository;
import com.daw.datamodel.repository.CurriculumRepository;
import com.daw.datamodel.repository.OfertaRepository;
import com.daw.datamodel.repository.PostulacionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PostulacionService {

    @Autowired
    private PostulacionRepository postulacionRepository;

    @Autowired
    private CiudadanoRepository ciudadanoRepository;

    @Autowired
    private OfertaRepository ofertaRepository;
    
    @Autowired
    private CurriculumRepository curriculumRepository;


    @Transactional
    public void postularOferta(String username, Long idOferta) {

        Ciudadano ciudadano = ciudadanoRepository
            .findByUsuarioNombreUsuario(username)
            .orElseThrow(() -> new RuntimeException("Ciudadano no encontrado"));

        Oferta oferta = ofertaRepository.findById(idOferta)
            .orElseThrow(() -> new RuntimeException("Oferta no encontrada"));

        if (postulacionRepository.existsByCiudadanoAndOferta(ciudadano, oferta)) {
            throw new RuntimeException("Ya estás inscrito en esta oferta");
        }

        if (ciudadano.getCurriculums().isEmpty()) {
            throw new RuntimeException("Debes subir un currículum antes de postular");
        }

        Curriculum cv = ciudadano.getCurriculums().get(0); 

        Postulacion postulacion = new Postulacion();
        postulacion.setCiudadano(ciudadano);
        postulacion.setOferta(oferta);
        postulacion.setCurriculum(cv);
        postulacion.setFechaPostulacion(LocalDateTime.now());

        postulacionRepository.save(postulacion);
    }


    

    public List<PostulacionDTO> listarPorCiudadano(Long idCiudadano) {
        Ciudadano ciudadano = ciudadanoRepository.findById(idCiudadano)
                .orElseThrow(() -> new RuntimeException("Ciudadano no encontrado"));

        List<Postulacion> postulaciones = postulacionRepository.findByCiudadano(ciudadano);
        List<PostulacionDTO> dtos = new ArrayList<>();

        for (Postulacion p : postulaciones) {
            PostulacionDTO dto = new PostulacionDTO();
            dto.setIdPostulacion(p.getIdPostulacion());
            dto.setFechaPostulacion(p.getFechaPostulacion());
            dto.setIdCiudadano(ciudadano.getIdCiudadano());
            dto.setNombreCiudadano(ciudadano.getNombre());
            dto.setApellidosCiudadano(ciudadano.getApellidos());
            dto.setProfesionCiudadano(ciudadano.getProfesion());
            dto.setIdOferta(p.getOferta().getIdOferta());
            dto.setTituloOferta(p.getOferta().getTitulo());
            dtos.add(dto);
        }

        return dtos;
    }

    @Transactional
    public void eliminarPostulacion(Long idPostulacion, Long idCiudadano) {
        // Buscar la postulación por id
        Postulacion postulacion = postulacionRepository.findById(idPostulacion)
            .orElseThrow(() -> new RuntimeException("Postulación no encontrada"));

        // Validar que pertenece al ciudadano que intenta eliminarla
        if (!postulacion.getCiudadano().getIdCiudadano().equals(idCiudadano)) {
            throw new RuntimeException("No tienes permiso para eliminar esta postulación");
        }

        // Eliminar la postulación
        postulacionRepository.delete(postulacion);
    }

    public List<EmpresaDTO> listarEmpresasPorCiudadano(Long idCiudadano) {
        Ciudadano ciudadano = ciudadanoRepository.findById(idCiudadano)
                .orElseThrow(() -> new RuntimeException("Ciudadano no encontrado"));

        List<Postulacion> postulaciones = postulacionRepository.findByCiudadano(ciudadano);
        List<EmpresaDTO> empresas = new ArrayList<>();

        for (Postulacion postulacion : postulaciones) {
            var empresa = postulacion.getOferta().getEmpresa();

            boolean yaAgregada = false;
            for (EmpresaDTO e : empresas) {
                if (e.getIdEmpresa().equals(empresa.getIdEmpresa())) {
                    yaAgregada = true;
                    break;
                }
            }
            if (yaAgregada) continue;

            EmpresaDTO dto = new EmpresaDTO();
            dto.setIdEmpresa(empresa.getIdEmpresa());
            dto.setNombreCompleto(empresa.getNombreCompleto());
            dto.setCif(empresa.getCif());
            dto.setActividadEmpresa(empresa.getActividadEmpresa());
            dto.setCalle(empresa.getCalle());
            dto.setNumero(empresa.getNumero());
            dto.setPoligono(empresa.getPoligono());
            dto.setLocalidad(empresa.getLocalidad());
            dto.setCodigoPostal(empresa.getCodigoPostal());
            dto.setTelefono(empresa.getTelefono());
            dto.setEmail(empresa.getEmail());
            dto.setDireccionWeb(empresa.getDireccionWeb());
            dto.setCodigoEmpresa(empresa.getCodigoEmpresa());
            dto.setValidado(empresa.getValidado());

            if (empresa.getUsuario() != null) {
                dto.setNombreUsuario(empresa.getUsuario().getNombreUsuario());
            }


            empresas.add(dto);
        }

        return empresas;
    }
    
    
    public List<PostulacionDTO> listarPostulacionesCiudadano(String username) {

        Ciudadano ciudadano = ciudadanoRepository
            .findByUsuarioNombreUsuario(username)
            .orElseThrow(() -> new RuntimeException("Ciudadano no encontrado"));

        return postulacionRepository.findByCiudadano(ciudadano)
            .stream()
            .map(p -> {
                PostulacionDTO dto = new PostulacionDTO();
                dto.setIdPostulacion(p.getIdPostulacion());
                dto.setIdOferta(p.getOferta().getIdOferta());
                dto.setTituloOferta(p.getOferta().getTitulo());
                dto.setFechaPostulacion(p.getFechaPostulacion());
                dto.setNombreEmpresa(p.getOferta().getEmpresa().getNombreCompleto());
                dto.setTipoOferta(p.getOferta().getTipoOferta());
                dto.setPerfilProfesional(p.getOferta().getPerfilProfesional());
                return dto;
            })
            .toList();
    }

    
    public List<CandidatoDTO> listarPostulantesDeOferta(Long idOferta) {
        List<Postulacion> postulaciones = postulacionRepository.findByOfertaIdOferta(idOferta);

        return postulaciones.stream().map(p -> {
            CandidatoDTO dto = new CandidatoDTO();
            dto.setIdPostulacion(p.getIdPostulacion());

            dto.setNombreCiudadano(p.getCiudadano().getNombre() + " " + p.getCiudadano().getApellidos());


            if (p.getCiudadano().getCurriculums() != null && !p.getCiudadano().getCurriculums().isEmpty()) {

                dto.setIdCurriculum(
                    p.getCiudadano().getCurriculums()
                     .get(p.getCiudadano().getCurriculums().size() - 1)
                     .getIdCurriculum()
                );

                dto.setFechaPostulacion(
                    p.getCiudadano().getCurriculums()
                     .get(p.getCiudadano().getCurriculums().size() - 1)
                     .getFechaSubida()
                );
            } else {
                dto.setIdCurriculum(null);
                dto.setFechaPostulacion(null); 
            }

            dto.setEstado(p.getEstado() != null ? p.getEstado().name() : EstadoPostulacion.PENDIENTE.name());

            return dto;
        }).collect(Collectors.toList());
    }

    // Contratar o rechazar candidato
    public void actualizarEstadoPostulacion(Long idPostulacion, String estado) {
        Postulacion postulacion = postulacionRepository.findById(idPostulacion)
            .orElseThrow(() -> new RuntimeException("Postulación no encontrada"));

        switch (estado.toUpperCase()) {
            case "CONTRATADO":
                postulacion.setEstado(EstadoPostulacion.CONTRATADO);
                break;
            case "RECHAZADO":
                postulacion.setEstado(EstadoPostulacion.RECHAZADO);
                break;
            default:
                throw new RuntimeException("Estado inválido: " + estado);
        }

        postulacionRepository.save(postulacion);
    }
    
    
    
    

}
