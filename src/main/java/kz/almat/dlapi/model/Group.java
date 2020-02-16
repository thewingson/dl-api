package kz.almat.dlapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Almat on 15.02.2020
 *
 * Model class for Group
 */

@Entity
@Table(name = "group")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    @Id
    @GeneratedValue(generator = "group_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "group_id_seq", name = "group_seq", allocationSize = 1)
    private Long id;

    @Column(name = "grade")
    private Long grade;

    @Column(name = "number")
    private Long number;

    @ManyToOne
    @JoinColumn(name = "department_id", foreignKey = @ForeignKey(name = "group_department_fk"), nullable = false)
    @JsonManagedReference
    private Department department;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(id, group.id) &&
                Objects.equals(grade, group.grade) &&
                Objects.equals(number, group.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, grade, number);
    }
}
