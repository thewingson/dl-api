package kz.almat.dlapi.service.impl;

import kz.almat.dlapi.model.Department;
import kz.almat.dlapi.model.Group;
import kz.almat.dlapi.pojo.GroupPOJO;
import kz.almat.dlapi.repository.DepartmentRepository;
import kz.almat.dlapi.repository.GroupRepository;
import kz.almat.dlapi.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Almat on 15.02.2020
 *
 * Implementation of service {@link GroupService}
 */

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, DepartmentRepository departmentRepository) {
        this.groupRepository = groupRepository;
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    @Override
    public void create(GroupPOJO groupPOJO) {
        Optional<Department> department = departmentRepository.findById(groupPOJO.getDepartmentId());
        department.ifPresent(d -> {
            Group group = new Group();
            group.setDepartment(d);
            group.setGrade(groupPOJO.getGrade());
            group.setListNumber(groupPOJO.getListNumber());
            groupRepository.save(group);
        });
    }

    @Transactional
    @Override
    public void createAll(List<GroupPOJO> groupPOJOS) {
        List<Group> groups = new ArrayList<>();
        groupPOJOS.forEach(g -> {
            Optional<Department> department = departmentRepository.findById(g.getDepartmentId());
            department.ifPresent(d -> {
                Group group = new Group();
                group.setDepartment(d);
                group.setGrade(g.getGrade());
                group.setListNumber(g.getListNumber());
                groups.add(group);
            });
        });
        groupRepository.saveAll(groups);
    }

    @Transactional
    @Override
    public void update(Long id, GroupPOJO groupPOJO) {
        Optional<Department> department = departmentRepository.findById(groupPOJO.getDepartmentId());
        department.ifPresent(d -> {
            Group group = new Group();
            group.setId(id);
            group.setDepartment(d);
            group.setGrade(groupPOJO.getGrade());
            group.setListNumber(groupPOJO.getListNumber());
            groupRepository.save(group);
        });
    }

}
