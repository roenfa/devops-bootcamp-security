package com.workshop.awscognitoidp.repositories;

import com.workshop.awscognitoidp.models.Subject;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Repository
public class SubjectRepository implements ISubjects{
    public final static List<Subject> subjects = new ArrayList<>(Arrays.asList(

//            new Subject("8173b807-bb58-4c33-8fa8-e7d2ade4bfe4","Networking",60),

            new Subject("Student_1","Networking",60),
            new Subject("Student_1","CD",80),
            new Subject("Student_1","Athena",65),
            new Subject("Student_1","Kubernetes",70),

            new Subject("Student_2","Networking",75),
            new Subject("Student_2","CD",90),
            new Subject("Student_2","Athena",70),
            new Subject("Student_2","Kubernetes",100),

            new Subject("Student_3","Networking",75),
            new Subject("Student_3","CD",70),
            new Subject("Student_3","Athena",80),
            new Subject("Student_3","Kubernetes",75)

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
    public void addResult(Subject subject) {
        subjects.add(subject);
    }
}