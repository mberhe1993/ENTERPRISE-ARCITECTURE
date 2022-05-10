package edu.miu.cs.cs544.RESTfulWebService.controller;

import edu.miu.cs.cs544.RESTfulWebService.domain.Department;
import edu.miu.cs.cs544.RESTfulWebService.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public List<Department> findAll(){
        return(departmentService.findAll());
    }
    @GetMapping(params = "paged=true")
    public Page<Department> findAll(Pageable pageable) {
        return (departmentService.findAll(pageable));
    }
}
