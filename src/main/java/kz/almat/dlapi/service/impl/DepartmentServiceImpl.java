package kz.almat.dlapi.service.impl;

import kz.almat.dlapi.model.Department;
import kz.almat.dlapi.model.Faculty;
import kz.almat.dlapi.pojo.DepartmentPOJO;
import kz.almat.dlapi.repository.DepartmentRepository;
import kz.almat.dlapi.repository.FacultyRepository;
import kz.almat.dlapi.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Almat on 10.02.2020
 * <p>
 * Implementation of service {@link DepartmentService}
 */

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final FacultyRepository facultyRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, FacultyRepository facultyRepository) {
        this.departmentRepository = departmentRepository;
        this.facultyRepository = facultyRepository;
    }

    @Transactional
    @Override
    public void create(DepartmentPOJO departmentPOJO) {
        Optional<Faculty> faculty = facultyRepository.findById(departmentPOJO.getFacultyId());
        Department department = new Department();
        faculty.ifPresent(f -> {
            department.setFaculty(f);
            department.setName(departmentPOJO.getName());
            department.setCode(departmentPOJO.getCode());
            departmentRepository.save(department);
        });
    }

    @Transactional
    @Override
    public void createAll(List<DepartmentPOJO> departmentPOJOS) {
        List<Department> departments = new ArrayList<>();
        departmentPOJOS.forEach(d -> {
            Optional<Faculty> faculty = facultyRepository.findById(d.getFacultyId());
            Department department = new Department();
            faculty.ifPresent(f -> {
                department.setFaculty(f);
                department.setName(d.getName());
                department.setCode(d.getCode());
                departments.add(department);
            });
        });
        departmentRepository.saveAll(departments);
    }

    @Transactional
    @Override
    public void update(Long id, DepartmentPOJO departmentPOJO) {
        Optional<Faculty> faculty = facultyRepository.findById(departmentPOJO.getFacultyId());
        Department department = new Department();
        faculty.ifPresent(f -> {
            department.setId(id);
            department.setFaculty(f);
            department.setName(departmentPOJO.getName());
            department.setCode(departmentPOJO.getCode());
            departmentRepository.save(department);
        });
    }
}
