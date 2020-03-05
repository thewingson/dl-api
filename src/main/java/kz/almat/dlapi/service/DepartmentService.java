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
    void create(DepartmentPOJO departmentPOJO);
    void createAll(List<DepartmentPOJO> departmentPOJOS);
    void update(Long id, DepartmentPOJO departmentPOJO);
}
