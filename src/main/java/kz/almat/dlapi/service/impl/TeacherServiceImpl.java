package kz.almat.dlapi.service.impl;

import kz.almat.dlapi.model.Department;
import kz.almat.dlapi.model.Teacher;
import kz.almat.dlapi.pojo.TeacherPOJO;
import kz.almat.dlapi.repository.DepartmentRepository;
import kz.almat.dlapi.repository.TeacherRepository;
import kz.almat.dlapi.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author almat_rakhmetolla on 17.02.2020
 *
 * Implementation of service {@link TeacherService}
 */

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, DepartmentRepository departmentRepository) {
        this.teacherRepository = teacherRepository;
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    @Override
    public void create(TeacherPOJO teacherPOJO) {
        Optional<Department> department = departmentRepository.findById(teacherPOJO.getDepartmentId());
        department.ifPresent(d -> {
            Teacher teacher = new Teacher();
            teacher.setDepartment(d);
            teacher.setFirstName(teacherPOJO.getFirstName());
            teacher.setLastName(teacherPOJO.getLastName());
            teacher.setMiddleName(teacherPOJO.getMiddleName());
            teacherRepository.save(teacher);
        });
    }

    @Transactional
    @Override
    public void createAll(List<TeacherPOJO> teacherPOJOS) {
        List<Teacher> teachers = new ArrayList<>();
        teacherPOJOS.forEach(t -> {
            Optional<Department> department = departmentRepository.findById(t.getDepartmentId());
            department.ifPresent(d -> {
                Teacher teacher = new Teacher();
                teacher.setDepartment(d);
                teacher.setFirstName(t.getFirstName());
                teacher.setLastName(t.getLastName());
                teacher.setMiddleName(t.getMiddleName());
                teachers.add(teacher);
            });
        });
        teacherRepository.saveAll(teachers);
    }

    @Transactional
    @Override
    public void update(Long id, TeacherPOJO teacherPOJO) {
        Optional<Department> department = departmentRepository.findById(teacherPOJO.getDepartmentId());
        department.ifPresent(d -> {
            Teacher teacher = new Teacher();
            teacher.setId(id);
            teacher.setDepartment(d);
            teacher.setFirstName(teacherPOJO.getFirstName());
            teacher.setLastName(teacherPOJO.getLastName());
            teacher.setMiddleName(teacherPOJO.getMiddleName());
            teacherRepository.save(teacher);
        });
    }

}
