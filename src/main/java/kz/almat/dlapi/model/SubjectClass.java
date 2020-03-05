package kz.almat.dlapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author almat_rakhmetolla on 17.02.2020
 *
 * Model class for Class(lesson, subscription)
 *
 * Subscription for classes where related {@link Subject}, {@link Group}, {@link Teacher}
 */

@Entity
@Table(name = "subject_class",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_subject_group_teacher", columnNames = {"subject_id", "group_id", "teacher_id"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectClass {

    @Id
    @GeneratedValue(generator = "subject_class_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "subject_class_id_seq", name = "subject_class_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subject_id", foreignKey = @ForeignKey(name = "subject_class_subject_fk"), nullable = false)
    @JsonManagedReference
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "subject_class_group_res_fk"), nullable = false)
    @JsonManagedReference
    private Group group;

    @ManyToOne
    @JoinColumn(name = "teacher_id", foreignKey = @ForeignKey(name = "subject_class_teacher_fk"), nullable = false)
    @JsonManagedReference
    private Teacher teacher;

    @ManyToMany
    @JoinTable(name = "task_subject_class",
            joinColumns = { @JoinColumn(name = "subject_class_id", foreignKey = @ForeignKey(name = "task_subject_class_task_fk")) },
            inverseJoinColumns = { @JoinColumn(name = "task_id", foreignKey = @ForeignKey(name = "task_subject_class_subject_class_fk")) })
    private Set<Task> tasks = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectClass that = (SubjectClass) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
