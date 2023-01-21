package com.workshop.awscognitoidp.crud.services;

import java.util.List;

public interface Service<S> {
    List<S> getAll();

    S getById(Long id);

    S insert(S p);

    S update(Long id, S p);

    S delete(Long id);
}
