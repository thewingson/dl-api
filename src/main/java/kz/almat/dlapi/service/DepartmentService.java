package kz.almat.dlapi.service;

import kz.almat.dlapi.model.Department;
import kz.almat.dlapi.pojo.DepartmentPOJO;

import java.util.List;

/**
 * @author Almat on 10.02.2020
 *
 * Service for {@link Department}
 */
public interface DepartmentService {
    Department create(DepartmentPOJO departmentPOJO);
    List<Department> createAll(List<DepartmentPOJO> departmentPOJO);
    Department update(Long id, DepartmentPOJO departmentPOJO);
    void delete(Long id);
}
