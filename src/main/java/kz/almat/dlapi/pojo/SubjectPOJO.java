package kz.almat.dlapi.pojo;

import kz.almat.dlapi.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author almat_rakhmetolla on 17.02.2020
 *
 * POJO for {@link Subject}
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectPOJO {

    private Long id;
    private String name;

}
