package kz.almat.dlapi.service;

import kz.almat.dlapi.model.Group;
import kz.almat.dlapi.pojo.GroupPOJO;
import kz.almat.dlapi.model.Group;

import java.util.List;

/**
 * @author Almat on 15.02.2020
 *
 * Service for {@link Group}
 */
public interface GroupService {
    Group create(GroupPOJO groupPOJO);
    List<Group> createAll(List<GroupPOJO> groupPOJOS);
    Group update(GroupPOJO groupPOJO);
    void delete(Long id);
}
