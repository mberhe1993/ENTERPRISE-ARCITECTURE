package edu.miu.cs.cs544.RESTfulWebService.repository;

import edu.miu.cs.cs544.RESTfulWebService.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
}
