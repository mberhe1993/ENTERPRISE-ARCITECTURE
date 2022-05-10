package edu.miu.cs.cs544.RESTfulWebService.service;

import edu.miu.cs.cs544.RESTfulWebService.domain.Culture;
import edu.miu.cs.cs544.RESTfulWebService.repository.CultureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CultureServiceImpl implements CultureService{

    @Autowired
    private CultureRepository cultureRepository;


    @Override
    public List<Culture> findAll() {
        return cultureRepository.findAll();
    }

    @Override
    public Optional<Culture> findById(String id) {
        return (cultureRepository.findById(id));
    }
}
