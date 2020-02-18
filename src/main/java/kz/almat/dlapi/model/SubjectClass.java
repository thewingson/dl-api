package kz.almat.dlapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author almat_rakhmetolla on 17.02.2020
 *
 * Model class for Class(lesson, subscription)
 *
 * Subscription for classes where related {@link Subject}, {@link Group}, {@link Teacher}
 */

@Entity
@Table(name = "subject_class")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectClass {

    @Id
    @GeneratedValue(generator = "subject_class_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "subject_class_id_seq", name = "subject_class_seq", allocationSize = 1)
    private Long id;

    //TODO: add JSON limits to lazy initialization. E.g. when you retrieve Group, it also takes related Department. Department takes Faculty. Too many @ManyToOne-s.
    @ManyToOne
    @JoinColumn(name = "subject_id", foreignKey = @ForeignKey(name = "subject_class_subject_fk"), nullable = false)
    @JsonManagedReference
    private Subject subject;

    //TODO: add JSON limits to lazy initialization. E.g. when you retrieve Group, it also takes related Department. Department takes Faculty. Too many @ManyToOne-s.
    @ManyToOne
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "subject_class_group_res_fk"), nullable = false)
    @JsonManagedReference
    private Group group;

    //TODO: add JSON limits to lazy initialization. E.g. when you retrieve Group, it also takes related Department. Department takes Faculty. Too many @ManyToOne-s.
    @ManyToOne
    @JoinColumn(name = "teacher_id", foreignKey = @ForeignKey(name = "subject_class_teacher_fk"), nullable = false)
    @JsonManagedReference
    private Teacher teacher;
}
