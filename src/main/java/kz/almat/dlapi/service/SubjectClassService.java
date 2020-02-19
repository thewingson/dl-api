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
    ResponseEntity<SubjectClass> create(SubjectClassPOJO subjectClassPOJO);
    ResponseEntity<SubjectClass> createAll(List<SubjectClassPOJO> subjectClassPOJOS);
    ResponseEntity<SubjectClass> update(SubjectClassPOJO subjectClassPOJO);
    void delete(Long id);
}
