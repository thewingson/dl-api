package kz.almat.dlapi.dto;

import kz.almat.dlapi.model.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Almat_Rakhmetolla on 05.03.2020
 *
 * DTO for {@link Group}
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {

    private Long id;
    private Long grade;
    private Long listNumber;
    private Long departmentId;
    private String departmentName;

}
