package com.daw.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.daw.datamodel.dto.CrearOfertaDTO;
import com.daw.datamodel.dto.DetalleOfertaDTO;
import com.daw.datamodel.dto.OfertaDTO;
import com.daw.datamodel.dto.OfertaPublicaDTO;
import com.daw.datamodel.entities.Empresa;
import com.daw.datamodel.entities.EstadoOferta;
import com.daw.datamodel.entities.Oferta;
import com.daw.datamodel.entities.Postulacion;
import com.daw.datamodel.repository.CiudadanoRepository;
import com.daw.datamodel.repository.EmpresaRepository;
import com.daw.datamodel.repository.OfertaRepository;
import com.daw.datamodel.repository.PostulacionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OfertaService {

	@Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private EmpresaRepository empresaRepository;
    
    @Autowired
    private PostulacionRepository postulacionRepository;
    
    @Autowired
    private CiudadanoRepository ciudadanoRepository;
     
    // CUANDO ESTE EL LOGIN HECHO HAY QUE CAMBIAR COMO RECIBE EL IDEMPRESA EN TODOS, HA QUE LO RECIBA AUTOMATICAMENTE CUANDO EL USUARIO ESTE LOGEADO
    
    public Oferta crearOferta(CrearOfertaDTO dto) {
        // Usuario logueado
        String nombreUsuario = SecurityContextHolder.getContext().getAuthentication().getName();

        // Buscar empresa
        Empresa empresa = empresaRepository.findByUsuarioNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        // Crear oferta
        Oferta oferta = new Oferta();
        oferta.setTitulo(dto.getTitulo());
        oferta.setDescripcion(dto.getDescripcion());
        oferta.setPerfilProfesional(dto.getPerfilProfesional());
        oferta.setTipoOferta(dto.getTipoOferta()); // String
        oferta.setDuracion(dto.getDuracion());
        oferta.setFechaPublicacion(LocalDate.now());
        oferta.setEstado(EstadoOferta.ABIERTA);
        oferta.setEmpresa(empresa);

        // Código de oferta
        oferta.setCodigoOferta(generarCodigoOferta(empresa));

        return ofertaRepository.save(oferta);
    }


    private String generarCodigoOferta(Empresa empresa) {
        Long numeroOfertas = ofertaRepository.countByEmpresa(empresa); 
        // %d -> formateo numero entero
        // 3 -> ancho de digitos que hay
        // 0 -> rellena de 0 la parte izquierda si es menor de 3 digitos
        String numeroFormateado = String.format("%03d", numeroOfertas + 1);
        return empresa.getCodigoEmpresa() + numeroFormateado;
    }

    public Oferta modificarOfertaPorUsuario(Long idOferta, OfertaDTO dto, String nombreUsuario) {
        Empresa empresa = empresaRepository.findByUsuarioNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        Oferta oferta = ofertaRepository.findById(idOferta)
                .orElseThrow(() -> new RuntimeException("Oferta no encontrada"));

        if (!oferta.getEmpresa().getIdEmpresa().equals(empresa.getIdEmpresa())) {
            throw new RuntimeException("No puedes modificar esta oferta");
        }

        oferta.setTitulo(dto.getTitulo());
        oferta.setDescripcion(dto.getDescripcion());
        oferta.setPerfilProfesional(dto.getPerfilProfesional());
        oferta.setTipoOferta(dto.getTipoOferta());
        oferta.setDuracion(dto.getDuracion());

        return ofertaRepository.save(oferta);
    }

    public void eliminarOfertaPorUsuario(Long idOferta, String nombreUsuario) {

        Empresa empresa = empresaRepository
            .findByUsuarioNombreUsuario(nombreUsuario)
            .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        Oferta oferta = ofertaRepository.findById(idOferta)
            .orElseThrow(() -> new RuntimeException("Oferta no encontrada"));

        if (!oferta.getEmpresa().getIdEmpresa().equals(empresa.getIdEmpresa())) {
            throw new RuntimeException("No tienes permiso para eliminar esta oferta");
        }

        postulacionRepository.deleteAll(oferta.getPostulaciones());
        ofertaRepository.delete(oferta);
    }


    public List<Oferta> buscarOfertasPorEmpresa(String nombreUsuario) {
        Empresa empresa = empresaRepository.findByUsuarioNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        return empresa.getOfertas();
    }

    public List<Postulacion> listarPostulaciones(Long idOferta) {
        Oferta oferta = ofertaRepository.findById(idOferta)
                .orElseThrow(() -> new RuntimeException("Oferta no encontrada"));
        return oferta.getPostulaciones();
    }
       
    public List<Oferta> obtenerOfertasPendientesVerificacion(Long idEmpresa) {
        Empresa empresa = empresaRepository.findById(idEmpresa)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        LocalDate fechaLimite = LocalDate.now().minusMonths(1);

        return ofertaRepository.findByEmpresaAndEstadoAndFechaPublicacionBefore(
                empresa, EstadoOferta.ABIERTA, fechaLimite
        );
    }
    
    public void finalizarOferta(Long idOferta) {
        Oferta oferta = ofertaRepository.findById(idOferta)
                .orElseThrow(() -> new RuntimeException("Oferta no encontrada"));

        oferta.setEstado(EstadoOferta.FINALIZADA);

        ofertaRepository.save(oferta);
    }
    
    public DetalleOfertaDTO obtenerDetalleOferta(Long idOferta) {
        Oferta oferta = ofertaRepository.findById(idOferta)
            .orElseThrow(() -> new RuntimeException("Oferta no encontrada"));

        DetalleOfertaDTO dto = new DetalleOfertaDTO();
        dto.setIdOferta(oferta.getIdOferta());
        dto.setTitulo(oferta.getTitulo());
        dto.setDescripcion(oferta.getDescripcion());
        dto.setPerfilProfesional(oferta.getPerfilProfesional());
        dto.setTipoOferta(oferta.getTipoOferta());
        dto.setDuracion(oferta.getDuracion());
        dto.setCodigoOferta(oferta.getCodigoOferta());
        dto.setFechaPublicacion(oferta.getFechaPublicacion());
        dto.setEstado(oferta.getEstado().toString());

        dto.setNombreEmpresa(oferta.getEmpresa().getNombreCompleto());
        dto.setActividadEmpresa(oferta.getEmpresa().getActividadEmpresa());

        return dto;
    }
      
    
    public List<OfertaPublicaDTO> listarOfertasPublicas(String tipo, String keyword) {

        List<Oferta> ofertas = ofertaRepository.listarOfertasPublicas(
                (tipo == null || tipo.isEmpty()) ? null : tipo,
                (keyword == null || keyword.isEmpty()) ? null : keyword
        );

        return ofertas.stream().map(o -> {
            OfertaPublicaDTO dto = new OfertaPublicaDTO();

            dto.setIdOferta(o.getIdOferta());
            dto.setTitulo(o.getTitulo());
            dto.setDescripcion(o.getDescripcion());
            dto.setPerfilProfesional(o.getPerfilProfesional());
            dto.setTipoOferta(o.getTipoOferta());
            dto.setDuracion(o.getDuracion());
            dto.setCodigoOferta(o.getCodigoOferta());
            dto.setFechaPublicacion(o.getFechaPublicacion());
            dto.setEstado(o.getEstado().toString());

            dto.setNombreEmpresa(o.getEmpresa().getNombreCompleto());
            dto.setActividadEmpresa(o.getEmpresa().getActividadEmpresa());

            return dto;
        }).collect(Collectors.toList());
    }
    
    
    public List<Oferta> obtenerOfertasPendientesVerificacionPorUsuario(String nombreUsuario) {

        Empresa empresa = empresaRepository
                .findByUsuarioNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        LocalDate fechaLimite = LocalDate.now().minusMonths(1);

        return ofertaRepository.ofertasPendientesVerificar(empresa, fechaLimite);
    }

    public List<OfertaDTO> listarOfertasConPostulacionesPorEmpresa(Long idEmpresa) {
        List<Oferta> ofertas = ofertaRepository.findByEmpresaIdEmpresa(idEmpresa);
        return ofertas.stream()
            .filter(o -> !o.getPostulaciones().isEmpty()) 
            .map(o -> {
                OfertaDTO dto = new OfertaDTO();
                dto.setIdOferta(o.getIdOferta());
                dto.setTitulo(o.getTitulo());
                dto.setEstado(o.getEstado());
                return dto;
            })
            .collect(Collectors.toList());
    }


}

