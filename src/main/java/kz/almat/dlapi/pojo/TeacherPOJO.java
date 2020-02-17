package kz.almat.dlapi.pojo;

import kz.almat.dlapi.model.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author almat_rakhmetolla on 17.02.2020
 *
 * POJO for {@link Teacher}
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherPOJO {

    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private Long departmentId;

}
