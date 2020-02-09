package kz.almat.dlapi.service;

import kz.almat.dlapi.model.Faculty;

import java.util.List;

/**
 * @author Almat on 09.02.2020
 *
 * Service for {@link Faculty}
 */
public interface FacultyService {

    Faculty create(Faculty faculty);
    List<Faculty> createAll(Iterable<Faculty> mIterable);
    Faculty update(Faculty faculty);
    void delete(Long id);


}
