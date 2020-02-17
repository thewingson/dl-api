package kz.almat.dlapi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import kz.almat.dlapi.model.Group;

/**
 * @author Almat on 15.02.2020
 *
 * POJO for {@link Group}
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupPOJO {

    private Long id;
    private Long grade;
    private Long listNumber;
    private Long departmentId;

}
