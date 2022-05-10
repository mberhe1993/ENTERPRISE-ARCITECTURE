package edu.miu.cs.cs544.RESTfulWebService.repository;

import edu.miu.cs.cs544.RESTfulWebService.domain.Culture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CultureRepository extends JpaRepository<Culture,String> {
}
