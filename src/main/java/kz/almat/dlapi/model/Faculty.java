package kz.almat.dlapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Almat on 09.02.2020
 *
 * Model class for Faculty
 */

@Entity
@Table(name = "faculty")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "name")
    @OneToMany(mappedBy = "faculty", cascade = CascadeType.REMOVE)
    @JsonBackReference
    private Set<Department> departments = new HashSet<>();

}
