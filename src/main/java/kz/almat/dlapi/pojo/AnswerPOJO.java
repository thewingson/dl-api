package kz.almat.dlapi.pojo;

import kz.almat.dlapi.model.Answer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Almat_Rakhmetolla on 03.03.2020
 *
 * POJO for {@link Answer}
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerPOJO {

    private Long id;
    private Long studentId;
    private Long taskId;
    private String message;

}
