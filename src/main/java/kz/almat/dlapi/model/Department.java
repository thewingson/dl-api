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
 * @author Almat on 10.02.2020
 *
 * Model class for Department
 */

@Entity
@Table(name = "department")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(generator = "department_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "department_id_seq", name = "department_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "faculty_id", foreignKey = @ForeignKey(name = "department_faculty_fk"), nullable = false)
    @JsonManagedReference
    private Faculty faculty;

    @OneToMany(mappedBy = "department", cascade = CascadeType.REMOVE)
    @JsonBackReference
    private Set<Group> groups  = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code);
    }
}
