package kz.almat.dlapi.service.impl;

import kz.almat.dlapi.pojo.SubjectClassPOJO;
import kz.almat.dlapi.repository.GroupRepository;
import kz.almat.dlapi.repository.SubjectClassRepository;
import kz.almat.dlapi.repository.SubjectRepository;
import kz.almat.dlapi.repository.TeacherRepository;
import kz.almat.dlapi.service.SubjectClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author almat_rakhmetolla on 17.02.2020
 * <p>
 * Implementation of service {@link SubjectClassService}
 */

//TODO: Exception Handling, and via Spring Annotation
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

    @Transactional
    @Override
    public void create(SubjectClassPOJO subjectClassPOJO) {
        subjectClassRepository.saveByIDs(subjectClassPOJO.getSubjectId(), subjectClassPOJO.getGroupId(), subjectClassPOJO.getTeacherId());
    }

    @Transactional
    @Override
    public void createAll(List<SubjectClassPOJO> subjectClassPOJOS) {
        for (SubjectClassPOJO s : subjectClassPOJOS) {
            subjectClassRepository.saveByIDs(s.getSubjectId(), s.getGroupId(), s.getTeacherId());
        }
    }

    @Transactional
    @Override
    public void update(Long id, SubjectClassPOJO subjectClassPOJO) {
        subjectClassRepository.updateById(id,
                subjectClassPOJO.getGroupId(),
                subjectClassPOJO.getTeacherId(),
                subjectClassPOJO.getId());
    }

}
