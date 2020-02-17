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
 *
 * 'GROUP_RES' is chosen by the reason of ORACLE's reservation of 'GROUP' key word.
 */

@Entity
@Table(name = "group_res")
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

    /**
     * Number of group in grade.
     *'NUMBER' is ORACLE's reserved key word. So, it's changed to 'list_number'.
     * */
    @Column(name = "list_number")
    private Long listNumber;

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
                Objects.equals(listNumber, group.listNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, grade, listNumber);
    }
}
