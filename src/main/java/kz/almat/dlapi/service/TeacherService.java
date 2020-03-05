package kz.almat.dlapi.service;

import kz.almat.dlapi.model.Teacher;
import kz.almat.dlapi.pojo.TeacherPOJO;

import java.util.List;

/**
 * @author almat_rakhmetolla on 17.02.2020
 *
 * Service for {@link Teacher}
 */
public interface TeacherService {
    void create(TeacherPOJO teacherPOJO);
    void createAll(List<TeacherPOJO> teacherPOJOS);
    void update(Long id, TeacherPOJO teacherPOJO);
}
