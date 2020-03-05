package kz.almat.dlapi.rest;

import kz.almat.dlapi.dto.GroupDTO;
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
    public List<GroupDTO> getAll() {
        return groupRepository.findAllConvertedToDTO();
    }

    @GetMapping("{id}")
    public Optional<GroupDTO> getOne(@PathVariable("id") Long id) {
        return groupRepository.findByIdConvertedToDTO(id);
    }

    @PostMapping
    public void add(@RequestBody GroupPOJO groupPOJO) {
        groupService.create(groupPOJO);
    }

    @PostMapping("/all")
    public void addAll(@RequestBody List<GroupPOJO> groupPOJOS) {
        groupService.createAll(groupPOJOS);
    }

    @PutMapping("{id}")
    public void edit(@PathVariable("id") Long id,
                      @RequestBody GroupPOJO groupPOJOS) {
        groupService.update(id, groupPOJOS);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        groupRepository.deleteById(id);
    }

    @DeleteMapping("/all")
    public void deleteAll() {
        groupRepository.deleteAll();
    }

}
