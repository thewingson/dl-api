package kz.almat.dlapi.service.impl;

import kz.almat.dlapi.model.SubjectClass;
import kz.almat.dlapi.pojo.SubjectClassPOJO;
import kz.almat.dlapi.repository.GroupRepository;
import kz.almat.dlapi.repository.SubjectClassRepository;
import kz.almat.dlapi.repository.SubjectRepository;
import kz.almat.dlapi.repository.TeacherRepository;
import kz.almat.dlapi.service.SubjectClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

/**
 * @author almat_rakhmetolla on 17.02.2020
 * <p>
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
    //TODO: Optimize INSERT and SELECT
    //TODO: Check for unique
    @Transactional
    @Override
    public ResponseEntity<SubjectClass> create(SubjectClassPOJO subjectClassPOJO) {
        try {
            subjectClassRepository.saveByIDs(subjectClassPOJO.getSubjectId(), subjectClassPOJO.getGroupId(), subjectClassPOJO.getTeacherId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    //TODO: POJO parsing to Aspects. SubjectClass Teacher from POJO make automated or use constructor.
    @Transactional
    @Override
    public ResponseEntity<SubjectClass> createAll(List<SubjectClassPOJO> subjectClassPOJOS) {
        for (SubjectClassPOJO s : subjectClassPOJOS) {
            try {
                subjectClassRepository.saveByIDs(s.getSubjectId(), s.getGroupId(), s.getTeacherId());
            } catch (SQLException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //TODO: POJO parsing to Aspects. SubjectClass Teacher from POJO make automated or use constructor.
    @Transactional
    @Override
    public ResponseEntity<SubjectClass> update(SubjectClassPOJO subjectClassPOJO) {
        try {
            subjectClassRepository.updateById(subjectClassPOJO.getSubjectId(),
                    subjectClassPOJO.getGroupId(),
                    subjectClassPOJO.getTeacherId(),
                    subjectClassPOJO.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @Override
    public void delete(Long id) {
        subjectClassRepository.deleteById(id);
    }
}
