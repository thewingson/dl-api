package kz.almat.dlapi.service;

import kz.almat.dlapi.model.Group;
import kz.almat.dlapi.pojo.GroupPOJO;

import java.util.List;

/**
 * @author Almat on 15.02.2020
 *
 * Service for {@link Group}
 */
public interface GroupService {
    void create(GroupPOJO groupPOJO);
    void createAll(List<GroupPOJO> groupPOJOS);
    void update(Long id, GroupPOJO groupPOJO);
}
