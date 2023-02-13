package vissoft.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vissoft.test.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>{
}
