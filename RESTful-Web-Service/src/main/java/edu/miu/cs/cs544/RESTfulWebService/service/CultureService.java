package edu.miu.cs.cs544.RESTfulWebService.service;

import edu.miu.cs.cs544.RESTfulWebService.domain.Culture;

import java.util.List;
import java.util.Optional;

public interface CultureService {

    List<Culture> findAll();
    Optional<Culture> findById(String id);
}
