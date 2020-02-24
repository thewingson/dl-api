package kz.almat.dlapi.pojo;

import kz.almat.dlapi.model.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Almat_Rakhmetolla on 24.02.2020
 *
 * POJO for {@link Task}
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskPOJO {

    private Long id;
    private Integer value;
    private String topic;
    private String description;
    private Timestamp deadline;
    private List<Long> subjectClasses = new ArrayList<>();

}
