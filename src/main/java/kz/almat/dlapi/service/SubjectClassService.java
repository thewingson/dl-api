package kz.almat.dlapi.service;

import kz.almat.dlapi.dto.SubjectClassDTO;
import kz.almat.dlapi.model.SubjectClass;
import kz.almat.dlapi.pojo.SubjectClassPOJO;

import java.util.List;

/**
 * @author almat_rakhmetolla on 17.02.2020
 *
 * Service for {@link SubjectClass}
 */
public interface SubjectClassService {
    SubjectClassDTO create(SubjectClassPOJO subjectClassPOJO);
    List<SubjectClass> createAll(List<SubjectClassPOJO> subjectClassPOJOS);
    SubjectClass update(SubjectClassPOJO subjectClassPOJO);
    void delete(Long id);
}
