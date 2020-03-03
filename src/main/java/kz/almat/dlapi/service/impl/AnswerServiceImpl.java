package kz.almat.dlapi.service.impl;

import kz.almat.dlapi.model.Answer;
import kz.almat.dlapi.model.AnswerDetail;
import kz.almat.dlapi.model.Student;
import kz.almat.dlapi.model.Task;
import kz.almat.dlapi.pojo.AnswerPOJO;
import kz.almat.dlapi.repository.AnswerRepository;
import kz.almat.dlapi.repository.StudentRepository;
import kz.almat.dlapi.repository.TaskRepository;
import kz.almat.dlapi.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Almat_Rakhmetolla on 03.03.2020
 *
 * Implementation of service {@link AnswerService}
 */

@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final TaskRepository taskRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository, TaskRepository taskRepository, StudentRepository studentRepository) {
        this.answerRepository = answerRepository;
        this.taskRepository = taskRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Answer create(AnswerPOJO answerPOJO) {
        Optional<Task> task = taskRepository.findById(answerPOJO.getTaskId());
        Optional<Student> student = studentRepository.findById(answerPOJO.getStudentId());
        AnswerDetail answerDetail = new AnswerDetail(answerPOJO.getMessage());
        Answer answer = new Answer();
        answer.setAnswerDetail(answerDetail);
        task.ifPresent(answer::setTask);
        student.ifPresent(answer::setStudent);
        return answerRepository.save(answer);
    }

    @Override
    public List<Answer> createAll(List<AnswerPOJO> answerPOJOS) {
        List<Answer> answers = new ArrayList<>();
        for (AnswerPOJO a : answerPOJOS) {
            Optional<Task> task = taskRepository.findById(a.getTaskId());
            Optional<Student> student = studentRepository.findById(a.getStudentId());
            AnswerDetail answerDetail = new AnswerDetail(a.getMessage());
            Answer answer = new Answer();
            answer.setAnswerDetail(answerDetail);
            task.ifPresent(answer::setTask);
            student.ifPresent(answer::setStudent);
            answers.add(answer);
        }
        return answerRepository.saveAll(answers);
    }

    @Override
    public Answer update(Long id, AnswerPOJO answerPOJO) {
        Optional<Task> task = taskRepository.findById(answerPOJO.getTaskId());
        Optional<Student> student = studentRepository.findById(answerPOJO.getStudentId());
        AnswerDetail answerDetail = new AnswerDetail(answerPOJO.getMessage());
        Answer answer = new Answer();
        answer.setId(id);
        answer.setAnswerDetail(answerDetail);
        task.ifPresent(answer::setTask);
        student.ifPresent(answer::setStudent);
        return answerRepository.save(answer);
    }

    @Override
    public void delete(Long id) {
        answerRepository.deleteById(id);
    }
}
