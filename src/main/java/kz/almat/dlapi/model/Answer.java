package kz.almat.dlapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Almat_Rakhmetolla on 24.02.2020
 *
 * Model class for Answer
 */

@Entity
@Table(name = "answer",
        uniqueConstraints = {
        @UniqueConstraint(name = "unique_student_task", columnNames = {"student_id", "task_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(generator = "answer_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "answer_id_seq", name = "answer_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "answer_student_fk"), nullable = false)
    @JsonManagedReference
    private Student student;

    @ManyToOne
    @JoinColumn(name = "task_id", foreignKey = @ForeignKey(name = "answer_task_fk"), nullable = false)
    @JsonManagedReference
    private Task task;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "message", column = @Column(name = "message"))
    })
    private AnswerDetail answerDetail;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(id, answer.id) &&
                Objects.equals(student, answer.student) &&
                Objects.equals(task, answer.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student, task);
    }
}
