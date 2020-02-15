package kz.almat.dlapi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Almat on 15.02.2020
 *
 * POJO class for {@link kz.almat.dlapi.model.Faculty}
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacultyPOJO {

    private Long id;
    private String name;

}
