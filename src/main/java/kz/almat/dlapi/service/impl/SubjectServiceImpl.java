package kz.almat.dlapi.service.impl;

import kz.almat.dlapi.model.Subject;
import kz.almat.dlapi.pojo.SubjectPOJO;
import kz.almat.dlapi.repository.SubjectRepository;
import kz.almat.dlapi.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author almat_rakhmetolla on 17.02.2020
 *
 * Implementation of service {@link SubjectService}
 */

@Service
public class SubjectServiceImpl implements SubjectService{

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    //TODO: POJO parsing to Aspects. Create Subject from POJO make automated or use constructor.
    @Transactional
    @Override
    public Subject create(SubjectPOJO subjectPOJO) {
        Subject subject = new Subject();
        subject.setName(subjectPOJO.getName());
        return subjectRepository.save(subject);
    }

    //TODO: POJO parsing to Aspects. Create Subject from POJO make automated or use constructor.
    @Transactional
    @Override
    public List<Subject> createAll(List<SubjectPOJO> subjectPOJOS) {
        List<Subject> subjects = new ArrayList<>();
        for (SubjectPOJO s: subjectPOJOS){
            Subject subject = new Subject();
            subject.setName(s.getName());
            subjects.add(subject);
        }
        return subjectRepository.saveAll(subjects);
    }

    //TODO: POJO parsing to Aspects. Create Subject from POJO make automated or use constructor.
    @Transactional
    @Override
    public Subject update(SubjectPOJO subjectPOJO) {
        Subject subject = new Subject();
        subject.setId(subjectPOJO.getId());
        subject.setName(subjectPOJO.getName());
        return subjectRepository.save(subject);
    }

    @Override
    public void delete(Long id) {
        subjectRepository.deleteById(id);
    }

}
