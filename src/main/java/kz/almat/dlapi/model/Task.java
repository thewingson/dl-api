package kz.almat.dlapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Almat_Rakhmetolla on 24.02.2020
 *
 * Model class for Task
 */

@Entity
@Table(name = "task")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(generator = "task_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "task_id_seq", name = "task_seq", allocationSize = 1)
    private Long id;

    @Column(name = "value", nullable = false)
    private Integer value;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "topic", column = @Column(name = "topic")),
            @AttributeOverride( name = "description", column = @Column(name = "description")),
            @AttributeOverride( name = "deadline", column = @Column(name = "deadline"))
    })
    private TaskDetail taskDetail;

    @ManyToMany(mappedBy = "tasks")
    private Set<SubjectClass> subjectClasses = new HashSet<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.REMOVE)
    @JsonBackReference
    private Set<Answer> answers = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                Objects.equals(value, task.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value);
    }
}
