package kz.almat.dlapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
 * Model class for Student
 */

//TODO: Abstract for Teacher and Student. Their all fields are same.
@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(generator = "student_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "student_id_seq", name = "student_seq", allocationSize = 1)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "middle_name", nullable = false)
    private String middleName;

    @ManyToOne
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "student_group_fk"), nullable = false)
    @JsonManagedReference
    private Group group;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    @JsonBackReference
    private Set<Answer> answers = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
