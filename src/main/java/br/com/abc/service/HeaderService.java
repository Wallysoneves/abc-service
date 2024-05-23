package br.com.abc.service;

import br.com.abc.domain.HeaderEntity;
import br.com.abc.domain.UserEntity;
import br.com.abc.infrastructure.exception.AbcException;
import br.com.abc.repository.HeaderRepository;
import br.com.abc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeaderService {

    @Autowired
    private HeaderRepository headerRepository;

    @Autowired
    private UserRepository userRepository;

    public HeaderEntity getById(Long id) {
        return headerRepository.findById(id).orElseThrow();
    }

    public List<HeaderEntity> getByUser(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new AbcException("user not found", HttpStatus.BAD_REQUEST));

        return headerRepository.findByUser(user);
    }

    public List<HeaderEntity> getAll() {
        return headerRepository.findAll();
    }

    public HeaderEntity create(HeaderEntity headerEntityCreate) {
        return headerRepository.save(headerEntityCreate);
    }

    public HeaderEntity update(HeaderEntity headerUpdate) {
        return headerRepository.save(headerUpdate);
    }

    public void delete(Long id) {
        headerRepository.deleteById(id);
    }
}
