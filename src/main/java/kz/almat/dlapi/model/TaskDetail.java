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
@Entity
@Table(name = "task_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDetail {

    @Id
    @GeneratedValue(generator = "task_detail_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "task_detail_id_seq", name = "task_detail_seq", allocationSize = 1)
    private Long id;

    @Column(name = "topic", nullable = false)
    private String topic;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "deadline", nullable = false)
    private Timestamp deadline;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "task_id", foreignKey = @ForeignKey(name = "task_detail_task_fk"), nullable = false)
    private Task task;

}
