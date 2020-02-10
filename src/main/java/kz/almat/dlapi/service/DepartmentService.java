package kz.almat.dlapi.service;

import kz.almat.dlapi.model.Department;
import kz.almat.dlapi.model.Faculty;

import java.util.List;

/**
 * @author Almat on 10.02.2020
 *
 * Service for {@link Department}
 */
public interface DepartmentService {

    Department create(Department department);
    List<Department> createAll(Iterable<Department> mIterable);
    Department update(Department department);
    void delete(Long id);

}
