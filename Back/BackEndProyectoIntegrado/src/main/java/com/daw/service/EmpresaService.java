package com.daw.service;

import java.text.Normalizer;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.daw.datamodel.dto.ActualizarEmpresaDTO;
import com.daw.datamodel.dto.EmpresaDTO;
import com.daw.datamodel.entities.Empresa;
import com.daw.datamodel.entities.Usuario;
import com.daw.datamodel.repository.EmpresaRepository;
import com.daw.datamodel.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Empresa crearEmpresa(EmpresaDTO dto) {
        // Crear usuario
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setContrasenaUsuario(passwordEncoder.encode(dto.getContrasenaUsuario()));
        usuario.setPerfil("EMPRESA");
        usuarioRepository.save(usuario);

        // Crear empresa
        Empresa empresa = new Empresa();
        empresa.setNombreCompleto(dto.getNombreCompleto());
        empresa.setCif(dto.getCif());
        empresa.setActividadEmpresa(dto.getActividadEmpresa());
        empresa.setCalle(dto.getCalle());
        empresa.setNumero(dto.getNumero());
        empresa.setPoligono(dto.getPoligono());
        empresa.setLocalidad(dto.getLocalidad());
        empresa.setCodigoPostal(dto.getCodigoPostal());
        empresa.setTelefono(dto.getTelefono());
        empresa.setEmail(dto.getEmail());
        empresa.setDireccionWeb(dto.getDireccionWeb());
        empresa.setUsuario(usuario);

        // Generar código empresa
        String codigoEmpresa = generarCodigoEmpresa(dto.getNombreCompleto(), dto.getCif());
        empresa.setCodigoEmpresa(codigoEmpresa);

        return empresaRepository.save(empresa);
    }

    public Empresa buscarPorId(Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
    }

    private String generarCodigoEmpresa(String nombreCompleto, String cif) {
        if (nombreCompleto == null || nombreCompleto.isBlank()) {
            return "NOMBRE_INVALIDO";
        }

        // 1️⃣ Normalizar y quitar tildes
        String nombreSinTilde = Normalizer.normalize(nombreCompleto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "") 
                .replaceAll("[^\\p{L}\\p{N}\\s]", "") 
                .trim();


        String codigoNombre;
        if (!nombreSinTilde.contains(" ")) {

            codigoNombre = nombreSinTilde.replaceAll("[^\\p{L}\\p{N}]", "").toUpperCase();
        } else {

            String[] partes = nombreSinTilde.split("\\s+");
            StringBuilder sb = new StringBuilder();
            for (String parte : partes) {
                if (!parte.isEmpty()) {
                    sb.append(Character.toUpperCase(parte.charAt(0)));
                }
            }
            codigoNombre = sb.toString();
        }

        String codigoCIF = (cif != null) ? cif.toUpperCase() : "";

        return codigoNombre + codigoCIF;
    }
    
    
    
    public List<EmpresaDTO> listarEmpresas() {
        List<Empresa> empresas = empresaRepository.findAll();
        
        // Convertir entidades a DTOs
        return empresas.stream().map(empresa -> {
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
            if (empresa.getUsuario() != null) {
                dto.setNombreUsuario(empresa.getUsuario().getNombreUsuario());
            }
            return dto;
        }).collect(Collectors.toList());
    }
    
    public Empresa actualizarEmpresa(Long idEmpresa, ActualizarEmpresaDTO dto) {

        Empresa empresa = empresaRepository.findById(idEmpresa)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        // Solo estos campos
        empresa.setNombreCompleto(dto.getNombreCompleto());
        empresa.setCif(dto.getCif());
        empresa.setActividadEmpresa(dto.getActividadEmpresa());
        empresa.setCalle(dto.getCalle());
        empresa.setNumero(dto.getNumero());
        empresa.setPoligono(dto.getPoligono());
        empresa.setLocalidad(dto.getLocalidad());
        empresa.setCodigoPostal(dto.getCodigoPostal());
        empresa.setTelefono(dto.getTelefono());
        empresa.setEmail(dto.getEmail());
        empresa.setDireccionWeb(dto.getDireccionWeb());

        return empresaRepository.save(empresa);
    }
    
    public Empresa buscarPorNombreUsuario(String nombreUsuario) {
        return empresaRepository.findByUsuarioNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
    }


    
}
