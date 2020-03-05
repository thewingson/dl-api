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
    void create(SubjectPOJO subjectPOJO);
    void createAll(List<SubjectPOJO> subjectPOJOS);
    void update(Long id, SubjectPOJO subjectPOJO);
}
