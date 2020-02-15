package kz.almat.dlapi.service.impl;

import kz.almat.dlapi.model.Faculty;
import kz.almat.dlapi.pojo.FacultyPOJO;
import kz.almat.dlapi.repository.FacultyRepository;
import kz.almat.dlapi.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Almat on 09.02.2020
 *
 * Implementation of service {@link FacultyService}
 */

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty create(FacultyPOJO facultyPOJO) {
        Faculty faculty = new Faculty();
        faculty.setName(facultyPOJO.getName());
        return facultyRepository.save(faculty);
    }

    @Transactional
    @Override
    public List<Faculty> createAll(List<FacultyPOJO> facultyPOJOS) {
        List<Faculty> faculties = new ArrayList<>();
        for(FacultyPOJO f: facultyPOJOS){
            Faculty faculty = new Faculty();
            faculty.setName(f.getName());
            faculties.add(faculty);
        }
        return facultyRepository.saveAll(faculties);
    }

    @Override
    public Faculty update(FacultyPOJO facultyPOJO) {
        Faculty faculty = new Faculty();
        faculty.setId(facultyPOJO.getId());
        faculty.setName(facultyPOJO.getName());
        return facultyRepository.save(faculty);
    }

    @Override
    public void delete(Long id) {
        facultyRepository.deleteById(id);
    }
}
