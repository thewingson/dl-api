package kz.almat.dlapi.pojo;

import kz.almat.dlapi.model.SubjectClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author almat_rakhmetolla on 17.02.2020
 *
 * POJO for {@link SubjectClass}
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectClassPOJO {

    private Long id;
    private Long subjectId;
    private Long groupId;
    private Long teacherId;

}
