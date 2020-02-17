package kz.almat.dlapi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import kz.almat.dlapi.model.Faculty;

/**
 * @author Almat on 15.02.2020
 *
 * POJO for {@link Faculty}
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacultyPOJO {

    private Long id;
    private String name;

}
