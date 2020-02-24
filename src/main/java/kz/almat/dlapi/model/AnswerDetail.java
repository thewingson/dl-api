package kz.almat.dlapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Almat_Rakhmetolla on 24.02.2020
 *
 * Model class for AnswerDetail which contains all addition info of particular {@link Answer}
 */

//TODO: Add field "file"
@Entity
@Table(name = "answer_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDetail {

    @Id
    @GeneratedValue(generator = "answer_detail_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "answer_detail_id_seq", name = "answer_detail_seq", allocationSize = 1)
    private Long id;

    @Column(name = "message")
    private String message;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "answer_id", foreignKey = @ForeignKey(name = "answer_detail_answer_fk"), nullable = false)
    private Answer answer;

}
