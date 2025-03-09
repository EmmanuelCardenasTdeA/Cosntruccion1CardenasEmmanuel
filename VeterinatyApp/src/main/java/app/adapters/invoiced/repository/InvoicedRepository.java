package app.adapters.invoiced.repository;

import app.domain.models.Person;

public interface InvoicedRepository {

    public boolean existPerson(long document);
    public void savePerson(Person person);
    public Person findByDocument(long document);
} 