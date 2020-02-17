package kz.almat.dlapi.service;

import kz.almat.dlapi.model.Subject;
import kz.almat.dlapi.pojo.SubjectPOJO;

import java.util.List;

/**
 * @author almat_rakhmetolla on 17.02.2020
 *
 * Service for {@link Subject}
 */
public interface SubjectService {
    Subject create(SubjectPOJO subjectPOJO);
    List<Subject> createAll(List<SubjectPOJO> subjectPOJOS);
    Subject update(SubjectPOJO subjectPOJO);
    void delete(Long id);
}
