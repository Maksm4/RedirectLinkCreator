package org.tpo10.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tpo10.Models.Link;

import java.util.List;
import java.util.Optional;

@Repository
public interface LinkRepository extends CrudRepository<Link, String> {

    Optional<Link> getLinkByNameAndPassword(String name, String password);

}
