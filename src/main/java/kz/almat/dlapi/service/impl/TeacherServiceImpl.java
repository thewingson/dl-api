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

    //TODO: POJO parsing to Aspects. Create Teacher from POJO make automated or use constructor.
    @Transactional
    @Override
    public Teacher create(TeacherPOJO teacherPOJO) {
        Optional<Department> department = departmentRepository.findById(teacherPOJO.getDepartmentId());
        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherPOJO.getFirstName());
        teacher.setLastName(teacherPOJO.getLastName());
        teacher.setMiddleName(teacherPOJO.getMiddleName());
        department.ifPresent(teacher::setDepartment);
        return teacherRepository.save(teacher);
    }

    //TODO: POJO parsing to Aspects. Create Teacher from POJO make automated or use constructor.
    @Transactional
    @Override
    public List<Teacher> createAll(List<TeacherPOJO> teacherPOJOS) {
        List<Teacher> teachers = new ArrayList<>();
        for (TeacherPOJO t: teacherPOJOS){
            Optional<Department> department = departmentRepository.findById(t.getDepartmentId());
            Teacher teacher = new Teacher();
            teacher.setFirstName(t.getFirstName());
            teacher.setLastName(t.getLastName());
            teacher.setMiddleName(t.getMiddleName());
            department.ifPresent(teacher::setDepartment);
            teachers.add(teacher);
        }
        return teacherRepository.saveAll(teachers);
    }

    //TODO: POJO parsing to Aspects. Create Teacher from POJO make automated or use constructor.
    @Transactional
    @Override
    public Teacher update(Long id, TeacherPOJO teacherPOJO) {
        Optional<Department> department = departmentRepository.findById(teacherPOJO.getDepartmentId());
        Teacher teacher = new Teacher();
        teacher.setId(teacherPOJO.getId());
        teacher.setFirstName(teacherPOJO.getFirstName());
        teacher.setLastName(teacherPOJO.getLastName());
        teacher.setMiddleName(teacherPOJO.getMiddleName());
        department.ifPresent(teacher::setDepartment);
        return teacherRepository.save(teacher);
    }

    @Override
    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }

}
