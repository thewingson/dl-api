package kz.almat.dlapi.dto;

import kz.almat.dlapi.model.SubjectClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author almat_rakhmetolla on 18.02.2020
 *
 * DTO for {@link SubjectClass}
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectClassDTO {

    private Long id;
    private Long subjectId;
    private String subjectName;
    private Long groupId;
    private String groupFullNumber;
    private Long teacherId;
    private String teacherFullName;

}
