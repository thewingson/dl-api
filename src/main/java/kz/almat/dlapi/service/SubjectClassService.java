package kz.almat.dlapi.service;

import kz.almat.dlapi.model.SubjectClass;
import kz.almat.dlapi.pojo.SubjectClassPOJO;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author almat_rakhmetolla on 17.02.2020
 *
 * Service for {@link SubjectClass}
 */
public interface SubjectClassService {
    void create(SubjectClassPOJO subjectClassPOJO);
    void createAll(List<SubjectClassPOJO> subjectClassPOJOS);
    void update(Long id, SubjectClassPOJO subjectClassPOJO);
}
