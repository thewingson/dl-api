package kz.almat.dlapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Almat_Rakhmetolla on 24.02.2020
 *
 * Model class for TaskDetail which contains all addition info of particular {@link Task}
 */

//TODO: Add field "file"
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDetail {

    @Column(name = "topic", nullable = false)
    private String topic;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "deadline", nullable = false)
    private Timestamp deadline;

}
