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

    @Transactional
    @Override
    public void create(SubjectPOJO subjectPOJO) {
        Subject subject = new Subject();
        subject.setName(subjectPOJO.getName());
        subjectRepository.save(subject);
    }

    @Transactional
    @Override
    public void createAll(List<SubjectPOJO> subjectPOJOS) {
        List<Subject> subjects = new ArrayList<>();
        subjectPOJOS.forEach(s -> {
            Subject subject = new Subject();
            subject.setName(s.getName());
            subjects.add(subject);
        });
        subjectRepository.saveAll(subjects);
    }

    @Transactional
    @Override
    public void update(Long id, SubjectPOJO subjectPOJO) {
        Subject subject = new Subject();
        subject.setId(id);
        subject.setName(subjectPOJO.getName());
        subjectRepository.save(subject);
    }


}
