package kz.almat.dlapi.pojo;

import kz.almat.dlapi.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author almat_rakhmetolla on 17.02.2020
 *
 * POJO for {@link Student}
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentPOJO {

    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private Long groupId;

}
