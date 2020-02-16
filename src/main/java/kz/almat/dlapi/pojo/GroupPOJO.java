package kz.almat.dlapi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Almat on 15.02.2020
 *
 * POJO for {@link kz.almat.dlapi.model.Group}
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupPOJO {

    private Long id;
    private Long grade;
    private Long number;
    private Long departmentId;

}
