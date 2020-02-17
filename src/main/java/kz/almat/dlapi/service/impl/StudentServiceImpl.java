package kz.almat.dlapi.service.impl;

import kz.almat.dlapi.model.Group;
import kz.almat.dlapi.model.Student;
import kz.almat.dlapi.pojo.StudentPOJO;
import kz.almat.dlapi.repository.GroupRepository;
import kz.almat.dlapi.repository.StudentRepository;
import kz.almat.dlapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author almat_rakhmetolla on 17.02.2020
 *
 * Implementation of service {@link StudentService}
 */

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    //TODO: POJO parsing to Aspects. Create Student from POJO make automated or use constructor.
    @Transactional
    @Override
    public Student create(StudentPOJO studentPOJO) {
        Optional<Group> group = groupRepository.findById(studentPOJO.getGroupId());
        Student student = new Student();
        student.setFirstName(studentPOJO.getFirstName());
        student.setLastName(studentPOJO.getLastName());
        student.setMiddleName(studentPOJO.getMiddleName());
        group.ifPresent(student::setGroup);
        return studentRepository.save(student);
    }

    //TODO: POJO parsing to Aspects. Create Student from POJO make automated or use constructor.
    @Transactional
    @Override
    public List<Student> createAll(List<StudentPOJO> studentPOJOS) {
        List<Student> students = new ArrayList<>();
        for (StudentPOJO s: studentPOJOS){
            Optional<Group> group = groupRepository.findById(s.getGroupId());
            Student student = new Student();
            student.setFirstName(s.getFirstName());
            student.setLastName(s.getLastName());
            student.setMiddleName(s.getMiddleName());
            group.ifPresent(student::setGroup);
            students.add(student);
        }
        return studentRepository.saveAll(students);
    }

    //TODO: POJO parsing to Aspects. Create Student from POJO make automated or use constructor.
    @Transactional
    @Override
    public Student update(StudentPOJO studentPOJO) {
        Optional<Group> group = groupRepository.findById(studentPOJO.getGroupId());
        Student student = new Student();
        student.setId(studentPOJO.getId());
        student.setFirstName(studentPOJO.getFirstName());
        student.setLastName(studentPOJO.getLastName());
        student.setMiddleName(studentPOJO.getMiddleName());
        group.ifPresent(student::setGroup);
        return studentRepository.save(student);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
