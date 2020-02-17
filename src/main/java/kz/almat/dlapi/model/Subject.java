package kz.almat.dlapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author almat_rakhmetolla on 17.02.2020
 *
 *  Model class for Subject
 */

@Entity
@Table(name = "subject")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(generator = "subject_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "subject_id_seq", name = "subject_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.REMOVE)
    @JsonBackReference
    private List<SubjectClass> subjectClasses = new ArrayList<>();

}
