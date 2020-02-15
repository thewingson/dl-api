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

    Faculty create(FacultyPOJO facultyPOJO);
    List<Faculty> createAll(List<FacultyPOJO> facultyPOJOS);
    Faculty update(FacultyPOJO facultyPOJOS);
    void delete(Long id);


}
