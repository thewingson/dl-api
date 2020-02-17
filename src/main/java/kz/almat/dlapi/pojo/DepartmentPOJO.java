package kz.almat.dlapi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import kz.almat.dlapi.model.Department;

/**
 * @author Almat on 15.02.2020
 *
 * POJO for {@link Department}
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentPOJO {

    private Long id;
    private String name;
    private String code;
    private Long facultyId;

}
