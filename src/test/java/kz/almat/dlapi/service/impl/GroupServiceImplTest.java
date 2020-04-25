package kz.almat.dlapi.service.impl;

import kz.almat.dlapi.model.Department;
import kz.almat.dlapi.model.Group;
import kz.almat.dlapi.pojo.GroupPOJO;
import kz.almat.dlapi.repository.DepartmentRepository;
import kz.almat.dlapi.repository.GroupRepository;
import kz.almat.dlapi.service.GroupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

/**
 * @author almat_rakhmetolla on 10.03.2020
 * <p>
 * Unit test for {@link GroupService}
 */

class GroupServiceImplTest extends AbstractServiceImplTest {

    @Autowired
    private GroupService groupService;

    @MockBean
    private GroupRepository groupRepository;

    @MockBean
    private DepartmentRepository departmentRepository;

    @Test
    void create_ok() throws Exception {
        GroupPOJO groupPOJO = new GroupPOJO(null, 1L, 1L, 1L);
        Department department = new Department();
        department.setId(1L);
        Group group = new Group(null, 1L, 1L, department, new HashSet<>(), new ArrayList<>());

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        groupService.create(groupPOJO);
        verify(groupRepository).save(group);
    }

    @Test
    void create_departmentNotFound() throws Exception {
        GroupPOJO groupPOJO = new GroupPOJO(null, 1L, 1L, 1L);

        when(departmentRepository.findById(1L)).thenReturn(Optional.empty());
        groupService.create(groupPOJO);
        verify(groupRepository, never()).save(any(Group.class));
    }

    @Test
    void createAll_ok() throws Exception {
        List<GroupPOJO> groupPOJOS = new ArrayList<>();
        groupPOJOS.add(new GroupPOJO(null, 1L, 1L, 1L));
        groupPOJOS.add(new GroupPOJO(null, 2L, 2L, 2L));
        Department department1 = new Department();
        department1.setId(1L);
        Department department2 = new Department();
        department2.setId(2L);
        List<Group> groups = new ArrayList<>();
        groups.add(new Group(null, 1L, 1L, department1, new HashSet<>(), new ArrayList<>()));
        groups.add(new Group(null, 2L, 2L, department2, new HashSet<>(), new ArrayList<>()));

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department1));
        when(departmentRepository.findById(2L)).thenReturn(Optional.of(department2));
        groupService.createAll(groupPOJOS);
        verify(groupRepository).saveAll(groups);
    }

    @Test
    void createAll_departmentNotFound() throws Exception {
        List<GroupPOJO> groupPOJOS = new ArrayList<>();
        groupPOJOS.add(new GroupPOJO(null, 1L, 1L, 1L));
        groupPOJOS.add(new GroupPOJO(null, 2L, 2L, 2L));
        List<Group> groups = new ArrayList<>();

        when(departmentRepository.findById(anyLong())).thenReturn(Optional.empty());
        groupService.createAll(groupPOJOS);
        verify(groupRepository).saveAll(groups);
    }

    @Test
    void update_ok() throws Exception {
        GroupPOJO groupPOJO = new GroupPOJO(null, 1L, 1L, 1L);
        Department department = new Department();
        department.setId(1L);
        Group group = new Group(1L, 1L, 1L, department, new HashSet<>(), new ArrayList<>());

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        groupService.update(1L, groupPOJO);
        verify(groupRepository).save(group);
    }

    @Test
    void update_departmentNotFound() throws Exception {
        GroupPOJO groupPOJO = new GroupPOJO(null, 1L, 1L, 1L);

        when(departmentRepository.findById(1L)).thenReturn(Optional.empty());
        groupService.update(1L, groupPOJO);
        verify(groupRepository, never()).save(any(Group.class));
    }
}