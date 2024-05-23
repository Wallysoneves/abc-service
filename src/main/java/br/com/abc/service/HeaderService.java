package br.com.abc.service;

import br.com.abc.domain.HeaderEntity;
import br.com.abc.repository.HeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeaderService {

    @Autowired
    private HeaderRepository headerRepository;

    public HeaderEntity getById(Long id) {
        return headerRepository.findById(id).orElseThrow();
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
