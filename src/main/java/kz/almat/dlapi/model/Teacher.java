package kz.almat.dlapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author almat_rakhmetolla on 17.02.2020
 *
 * Model class for Teacher
 */

@Entity
@Table(name = "teacher")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(generator = "teacher_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "teacher_id_seq", name = "teacher_seq", allocationSize = 1)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "middle_name", nullable = false)
    private String middleName;

    @ManyToOne
    @JoinColumn(name = "department_id", foreignKey = @ForeignKey(name = "teacher_department_fk"), nullable = false)
    @JsonManagedReference
    private Department department;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.REMOVE)
    @JsonBackReference
    private List<SubjectClass> subjectClasses = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(id, teacher.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
