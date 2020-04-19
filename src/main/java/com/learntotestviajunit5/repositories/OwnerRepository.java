package com.learntotestviajunit5.repositories;


import com.learntotestviajunit5.model.Owner;
import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
