package kz.almat.dlapi.rest;

import kz.almat.dlapi.model.Group;
import kz.almat.dlapi.pojo.GroupPOJO;
import kz.almat.dlapi.repository.GroupRepository;
import kz.almat.dlapi.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Almat on 15.02.2020
 *
 * Rest for {@link Group}
 */

@RestController
@RequestMapping("/group")
public class GroupRest {

    private final GroupRepository groupRepository;
    private final GroupService groupService;

    @Autowired
    public GroupRest(GroupRepository groupRepository, GroupService groupService) {
        this.groupRepository = groupRepository;
        this.groupService = groupService;
    }

    @GetMapping
    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<Group> getOne(@PathVariable("id") Long id) {
        return groupRepository.findById(id);
    }

    @PostMapping
    public Group add(@RequestBody GroupPOJO groupPOJO) {
        return groupService.create(groupPOJO);
    }

    @PostMapping("/all")
    public List<Group> addAll(@RequestBody List<GroupPOJO> groupPOJOS) {
        return groupService.createAll(groupPOJOS);
    }

    @PutMapping
    public Group edit(@RequestBody GroupPOJO groupPOJOS) {
        return groupService.update(groupPOJOS);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        groupService.delete(id);
    }

    @DeleteMapping("/all")
    public void deleteAll() {
        groupRepository.deleteAll();
    }

}
