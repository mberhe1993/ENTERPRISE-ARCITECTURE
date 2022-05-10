package edu.miu.cs.cs544.RESTfulWebService.service;

import edu.miu.cs.cs544.RESTfulWebService.domain.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentService {

    List<Department> findAll();
    Page<Department> findAll(Pageable pageable);
}
