package kz.almat.dlapi.service.impl;

import kz.almat.dlapi.model.Faculty;
import kz.almat.dlapi.pojo.FacultyPOJO;
import kz.almat.dlapi.repository.FacultyRepository;
import kz.almat.dlapi.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Almat on 09.02.2020
 *
 * Implementation of service {@link FacultyService}
 */

//TODO: POJO parsing to Aspects.
@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public void create(FacultyPOJO facultyPOJO) {
        Faculty faculty = new Faculty();
        faculty.setName(facultyPOJO.getName());
        facultyRepository.save(faculty);
    }

    @Override
    public void createAll(List<FacultyPOJO> facultyPOJOS) {
        List<Faculty> faculties = new ArrayList<>();

        facultyPOJOS.forEach(f -> {
            Faculty faculty = new Faculty();
            faculty.setName(f.getName());
            faculties.add(faculty);
        });
        facultyRepository.saveAll(faculties);
    }

    @Override
    public void update(Long id, FacultyPOJO facultyPOJO) {
        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(facultyPOJO.getName());
        facultyRepository.save(faculty);
    }

}
