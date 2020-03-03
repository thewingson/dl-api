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

    //TODO: POJO parsing to Aspects. Create Group from POJO make automated or use constructor.
    @Transactional
    @Override
    public Group create(GroupPOJO groupPOJO) {
        Optional<Department> department = departmentRepository.findById(groupPOJO.getDepartmentId());
        Group group = new Group();
        group.setGrade(groupPOJO.getGrade());
        group.setListNumber(groupPOJO.getListNumber());
        department.ifPresent(group::setDepartment);
        return groupRepository.save(group);
    }

    //TODO: POJO parsing to Aspects. Create Group from POJO make automated or use constructor.
    @Transactional
    @Override
    public List<Group> createAll(List<GroupPOJO> groupPOJOS) {
        List<Group> groups = new ArrayList<>();
        for (GroupPOJO g: groupPOJOS){
            Optional<Department> department = departmentRepository.findById(g.getDepartmentId());
            Group group = new Group();
            group.setGrade(g.getGrade());
            group.setListNumber(g.getListNumber());
            department.ifPresent(group::setDepartment);
            groups.add(group);
        }
        return groupRepository.saveAll(groups);
    }

    //TODO: POJO parsing to Aspects. Create Group from POJO make automated or use constructor.
    @Transactional
    @Override
    public Group update(Long id, GroupPOJO groupPOJO) {
        Optional<Department> department = departmentRepository.findById(groupPOJO.getDepartmentId());
        Group group = new Group();
        group.setId(id);
        group.setGrade(groupPOJO.getGrade());
        group.setListNumber(groupPOJO.getListNumber());
        department.ifPresent(group::setDepartment);
        return groupRepository.save(group);
    }

    @Override
    public void delete(Long id) {
        groupRepository.deleteById(id);
    }
}
