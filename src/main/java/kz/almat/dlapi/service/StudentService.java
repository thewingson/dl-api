package kz.almat.dlapi.service;

import kz.almat.dlapi.model.Student;
import kz.almat.dlapi.pojo.StudentPOJO;

import java.util.List;

/**
 * @author almat_rakhmetolla on 17.02.2020
 *
 * Service for {@link Student}
 */
public interface StudentService {
    void create(StudentPOJO studentPOJO);
    void createAll(List<StudentPOJO> studentPOJOS);
    void update(Long id, StudentPOJO studentPOJO);
}
