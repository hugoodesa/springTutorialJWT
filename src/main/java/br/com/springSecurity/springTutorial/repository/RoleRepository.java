package br.com.springSecurity.springTutorial.repository;

import br.com.springSecurity.springTutorial.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {

//    @Query(value = "SELECT count(*)>0  from roles r where r.id = :id",nativeQuery = true)
//    public boolean isRoleValid (Long id);
//    @Query(value = "SELECT count(*)>0  from roles r where r.id = :id",nativeQuery = true)
//    public boolean isRoleValid (Long id);

}