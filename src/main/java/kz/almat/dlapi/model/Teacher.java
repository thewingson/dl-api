package kz.almat.dlapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author almat_rakhmetolla on 17.02.2020
 *
 * Model class for Teacher
 */

//TODO: Abstract for Teacher and Student. Their all fields are same.
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


    //TODO: add JSON limits to lazy initialization. E.g. when you retrieve Group, it also takes related Department. Department takes Faculty. Too many @ManyToOne-s.
    @ManyToOne
    @JoinColumn(name = "department_id", foreignKey = @ForeignKey(name = "teacher_department_fk"), nullable = false)
    @JsonManagedReference
    private Department department;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(id, teacher.id) &&
                Objects.equals(firstName, teacher.firstName) &&
                Objects.equals(lastName, teacher.lastName) &&
                Objects.equals(middleName, teacher.middleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, middleName);
    }

}
