package kz.almat.dlapi.service.impl;

import kz.almat.dlapi.model.Group;
import kz.almat.dlapi.model.Subject;
import kz.almat.dlapi.model.SubjectClass;
import kz.almat.dlapi.model.Teacher;
import kz.almat.dlapi.pojo.SubjectClassPOJO;
import kz.almat.dlapi.repository.GroupRepository;
import kz.almat.dlapi.repository.SubjectClassRepository;
import kz.almat.dlapi.repository.SubjectRepository;
import kz.almat.dlapi.repository.TeacherRepository;
import kz.almat.dlapi.service.SubjectClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author almat_rakhmetolla on 17.02.2020
 *
 * Implementation of service {@link SubjectClassService}
 */

@Service
public class SubjectClassServiceImpl implements SubjectClassService {

    private final SubjectClassRepository subjectClassRepository;
    private final SubjectRepository subjectRepository;
    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public SubjectClassServiceImpl(SubjectClassRepository subjectClassRepository,
                                   SubjectRepository subjectRepository,
                                   GroupRepository groupRepository,
                                   TeacherRepository teacherRepository) {
        this.subjectClassRepository = subjectClassRepository;
        this.subjectRepository = subjectRepository;
        this.groupRepository = groupRepository;
        this.teacherRepository = teacherRepository;
    }

    //TODO: POJO parsing to Aspects. SubjectClass Teacher from POJO make automated or use constructor.
    @Transactional
    @Override
    public SubjectClass create(SubjectClassPOJO subjectClassPOJO) {
        Optional<Subject> subject = subjectRepository.findById(subjectClassPOJO.getSubjectId());
        Optional<Group> group = groupRepository.findById(subjectClassPOJO.getGroupId());
        Optional<Teacher> teacher = teacherRepository.findById(subjectClassPOJO.getTeacherId());
        SubjectClass subjectClass = new SubjectClass();
        subject.ifPresent(subjectClass::setSubject);
        group.ifPresent(subjectClass::setGroup);
        teacher.ifPresent(subjectClass::setTeacher);
        return subjectClassRepository.save(subjectClass);
    }

    //TODO: POJO parsing to Aspects. SubjectClass Teacher from POJO make automated or use constructor.
    @Transactional
    @Override
    public List<SubjectClass> createAll(List<SubjectClassPOJO> subjectClassPOJOS) {
        List<SubjectClass> subjectClasses = new ArrayList<>();
        for (SubjectClassPOJO s: subjectClassPOJOS){
            Optional<Subject> subject = subjectRepository.findById(s.getSubjectId());
            Optional<Group> group = groupRepository.findById(s.getGroupId());
            Optional<Teacher> teacher = teacherRepository.findById(s.getTeacherId());
            SubjectClass subjectClass = new SubjectClass();
            subject.ifPresent(subjectClass::setSubject);
            group.ifPresent(subjectClass::setGroup);
            teacher.ifPresent(subjectClass::setTeacher);
            subjectClasses.add(subjectClass);
        }
        return subjectClassRepository.saveAll(subjectClasses);
    }

    //TODO: POJO parsing to Aspects. SubjectClass Teacher from POJO make automated or use constructor.
    @Transactional
    @Override
    public SubjectClass update(SubjectClassPOJO subjectClassPOJO) {
        Optional<Subject> subject = subjectRepository.findById(subjectClassPOJO.getSubjectId());
        Optional<Group> group = groupRepository.findById(subjectClassPOJO.getGroupId());
        Optional<Teacher> teacher = teacherRepository.findById(subjectClassPOJO.getTeacherId());
        SubjectClass subjectClass = new SubjectClass();
        subjectClass.setId(subjectClassPOJO.getId());
        subject.ifPresent(subjectClass::setSubject);
        group.ifPresent(subjectClass::setGroup);
        teacher.ifPresent(subjectClass::setTeacher);
        return subjectClassRepository.save(subjectClass);
    }

    @Override
    public void delete(Long id) {
        subjectClassRepository.deleteById(id);
    }
}
