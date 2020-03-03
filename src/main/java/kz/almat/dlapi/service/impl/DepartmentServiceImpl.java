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

    //TODO: POJO parsing to Aspects. Create Department from POJO make automated or use constructor.
    @Transactional
    @Override
    public Department create(DepartmentPOJO departmentPOJO) {
        Optional<Faculty> faculty = facultyRepository.findById(departmentPOJO.getFacultyId());
        Department department = new Department();
        department.setName(departmentPOJO.getName());
        department.setCode(departmentPOJO.getCode());
        faculty.ifPresent(department::setFaculty);
        return departmentRepository.save(department);
    }

    //TODO: POJO parsing to Aspects. Create Department from POJO make automated or use constructor.
    @Transactional
    @Override
    public List<Department> createAll(List<DepartmentPOJO> departmentPOJO) {
        List<Department> departments = new ArrayList<>();
        for (DepartmentPOJO d: departmentPOJO){
            Optional<Faculty> faculty = facultyRepository.findById(d.getFacultyId());
            Department department = new Department();
            department.setName(d.getName());
            department.setCode(d.getCode());
            faculty.ifPresent(department::setFaculty);
            departments.add(department);
        }
        return departmentRepository.saveAll(departments);
    }

    //TODO: POJO parsing to Aspects. Create Department from POJO make automated.
    @Transactional
    @Override
    public Department update(Long id, DepartmentPOJO departmentPOJO) {
        Optional<Faculty> faculty = facultyRepository.findById(departmentPOJO.getFacultyId());
        Department department = new Department();
        department.setId(departmentPOJO.getId());
        department.setName(departmentPOJO.getName());
        department.setCode(departmentPOJO.getCode());
        faculty.ifPresent(department::setFaculty);
        return departmentRepository.save(department);
    }

    @Override
    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }
}
