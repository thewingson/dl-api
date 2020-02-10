package kz.almat.dlapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @Column
    private String name;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Department> departments;

}
