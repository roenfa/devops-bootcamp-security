package com.workshop.awscognitoidp.crud.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;

import com.workshop.awscognitoidp.crud.models.Bootcamp;
import com.workshop.awscognitoidp.crud.models.Subject;
import com.workshop.awscognitoidp.crud.models.Trainer;
import com.workshop.awscognitoidp.crud.repositories.BootcampRepository;
import com.workshop.awscognitoidp.crud.repositories.SubjectRepository;
import com.workshop.awscognitoidp.crud.repositories.TrainerRepository;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class TrainerServiceImp implements Service<Trainer>{

    @Autowired
    TrainerRepository trainerRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    BootcampRepository bootcampRepository;

    @Override
    public List<Trainer> getAll() {
        var it = trainerRepository.findAll();
        var orders = new ArrayList<Trainer>();
        it.forEach(e -> {
            Hibernate.initialize(e.getSubjects());
            Hibernate.initialize(e.getBootcamps());
            orders.add(e);
        });

        return orders;
    }

    @Override
    public Trainer getById(Long id) {
        Trainer o = trainerRepository.findById(id).get();
        return o;
    }

    @Override
    public Trainer insert(Trainer o) {
        if(o.getBootcamps() == null || o.getSubjects() == null){
            return trainerRepository.save(o);
        }
        o.getSubjects()
            .stream()
            .map(p -> {
                Subject subject = subjectRepository.findById(p.getId()).get();
                return subject;
            });
        
        o.getBootcamps()
            .stream()
            .map(p -> {
                Bootcamp bootcamp = bootcampRepository.findById(p.getId()).get();
                return bootcamp;
            });

        return trainerRepository.save(o);
    }

    @Override
    public Trainer update(Long id, Trainer o) {
        Trainer trainerToUpdate = trainerRepository.findById(id).get();
        Trainer trainerUpdated = null;
        if (trainerToUpdate != null) {
            trainerUpdated = trainerRepository.save(o);
         }

        return trainerUpdated;
    }

    @Override
    public Trainer delete(Long id) {
        Trainer trainer = trainerRepository.findById(id).get();
        
        if (trainer != null) {
            trainerRepository.delete(trainer);
        }

        return trainer;
    }
}
