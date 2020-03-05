package kz.almat.dlapi.dto;

import kz.almat.dlapi.model.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Almat_Rakhmetolla on 05.03.2020
 *
 * DTO for {@link Teacher}
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private Long departmentId;
    private String departmentName;
    private String departmentCode;

}
