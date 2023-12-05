package br.com.fiap.vertigo.service;


import br.com.fiap.vertigo.dto.LoginDTO;
import br.com.fiap.vertigo.dto.UsuarioDTO;
import br.com.fiap.vertigo.model.Usuario;
import br.com.fiap.vertigo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<Usuario> listAll(Pageable pageRequest) {
        return usuarioRepository.findAll(pageRequest);
    }

    public Usuario findById(Long id) {
        Usuario entity = findEntityById(id);
        return entity;
    }

    @Transactional
    public Usuario create(UsuarioDTO newData) {
        Usuario entity = convertToEntity(newData);
        Usuario savedEntity = usuarioRepository.save(entity);
        return savedEntity;
    }

    @Transactional
    public Usuario update(Long id, UsuarioDTO updatedData) {
        findEntityById(id);
        updatedData.setId(id);
        Usuario updatedEntity = convertToEntity(updatedData);
        Usuario savedEntity = usuarioRepository.save(updatedEntity);
        return savedEntity;
    }

    @Transactional
    public void delete(Long id) {
        Usuario entity = findEntityById(id);
        usuarioRepository.delete(entity);
    }

    public Usuario findEntityById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "(" + getClass().getSimpleName() + ") - Usuario não encontrado(a) por ID: " + id));
    }

    // private UsuarioDTO convertToDto(Usuario entity) {
    //     UsuarioDTO dto = new UsuarioDTO();
    //     dto.setId(entity.getId());
    //     dto.setEmail(entity.getEmail());
    //     dto.setSenha(entity.getSenha());
    //
    //     return dto;
    // }

    private Usuario convertToEntity(UsuarioDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("(" + getClass().getSimpleName() + ") - UsuarioDTO não pode ser nulo.");
        }
        Usuario entity;
        if (dto.getId() != null) {
            entity = findEntityById(dto.getId());
            entity.setNome(dto.getNome());
            entity.setEmail(dto.getEmail());
            entity.setSenha(dto.getSenha());
            entity.setUrlImagem(dto.getUrlImagem());
        } else {
            entity = new Usuario();
            entity.setNome(dto.getNome());
            entity.setEmail(dto.getEmail());
            entity.setSenha(dto.getSenha());
            entity.setUrlImagem(dto.getUrlImagem());
        }
        return entity;
    }

    public Optional<Usuario> validarLogin(LoginDTO loginDTO) {
        return usuarioRepository.findByEmail(loginDTO.getEmail())
                .filter(entity -> entity.getSenha().equals(loginDTO.getSenha()));
    }
}
