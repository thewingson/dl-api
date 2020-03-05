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

    @Transactional
    @Override
    public void create(StudentPOJO studentPOJO) {
        Optional<Group> group = groupRepository.findById(studentPOJO.getGroupId());
        group.ifPresent(g -> {
            Student student = new Student();
            student.setGroup(g);
            student.setFirstName(studentPOJO.getFirstName());
            student.setLastName(studentPOJO.getLastName());
            student.setMiddleName(studentPOJO.getMiddleName());
            studentRepository.save(student);
        });
    }

    @Transactional
    @Override
    public void createAll(List<StudentPOJO> studentPOJOS) {
        List<Student> students = new ArrayList<>();
        studentPOJOS.forEach(s -> {
            Optional<Group> group = groupRepository.findById(s.getGroupId());
            group.ifPresent(g -> {
                Student student = new Student();
                student.setGroup(g);
                student.setFirstName(s.getFirstName());
                student.setLastName(s.getLastName());
                student.setMiddleName(s.getMiddleName());
                students.add(student);
            });
        });
        studentRepository.saveAll(students);
    }

    @Transactional
    @Override
    public void update(Long id, StudentPOJO studentPOJO) {
        Optional<Group> group = groupRepository.findById(studentPOJO.getGroupId());
        group.ifPresent(g -> {
            Student student = new Student();
            student.setId(id);
            student.setGroup(g);
            student.setFirstName(studentPOJO.getFirstName());
            student.setLastName(studentPOJO.getLastName());
            student.setMiddleName(studentPOJO.getMiddleName());
            studentRepository.save(student);
        });
    }
}
