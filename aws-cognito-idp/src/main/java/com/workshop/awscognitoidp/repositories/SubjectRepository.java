package com.workshop.awscognitoidp.repositories;

import com.workshop.awscognitoidp.models.Subject;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Repository
public class SubjectRepository implements ISubjects{
    public final static List<Subject> subjects = new ArrayList<>(Arrays.asList(
            new Subject("st1","infrastructure",80),
            new Subject("st1","backend",70),
            new Subject("st1","frontend",85),
            new Subject("st1","python",90),
            new Subject("st1","container",90),
            new Subject("st2","infrastructure",100),
            new Subject("st2","backend",80),
            new Subject("st2","frontend",80),
            new Subject("st2","python",70),
            new Subject("st2","container",75),
            new Subject("st3","infrastructure",90),
            new Subject("st3","backend",60),
            new Subject("st3","frontend",95),
            new Subject("st3","python",90),
            new Subject("st3","container",100)
));


    @Override
    public List<Subject> getAll() {
        return subjects;
    }

    @Override
    public List<Subject> getByStudentId(String studentId) {
        List<Subject> studentSubjectList = new ArrayList<>();
        for (Subject s: subjects) {
            if(s.getStudentId().equals(studentId)){
                studentSubjectList.add(s);
            }
        }
        return studentSubjectList;
    }

    @Override
    public void addScore(Subject subject) {
        subjects.add(subject);
    }
}
