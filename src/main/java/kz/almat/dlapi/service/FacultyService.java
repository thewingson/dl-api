package kz.almat.dlapi.service;

import kz.almat.dlapi.model.Faculty;
import kz.almat.dlapi.pojo.FacultyPOJO;

import java.util.List;

/**
 * @author Almat on 09.02.2020
 *
 * Service for {@link Faculty}
 */
public interface FacultyService {
    void create(FacultyPOJO facultyPOJO);
    void createAll(List<FacultyPOJO> facultyPOJOS);
    void update(Long id, FacultyPOJO facultyPOJO);
}
