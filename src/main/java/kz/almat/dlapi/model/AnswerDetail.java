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
    @Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDetail {

    @Column(name = "message")
    private String message;
}
