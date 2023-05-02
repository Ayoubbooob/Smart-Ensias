package com.ensias.ensiasattendease.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ensias.ensiasattendease.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAll() ;
    Student findByCne(String cne) ;
    void delete(Student student);
    void deleteByCne(String cne) ;

//    @Query(value = """
//      select t from Token t inner join User u\s
//      on t.user.id = u.id\s
//      where u.id = :id and (t.expired = false or t.revoked = false)\s
//      """)
//    @Override
//    Optional<Student> findById(Long aLong);



    @Query("SELECT s FROM Student s WHERE s.id = :id")
    Student findStudentById(@Param("id") Long id);
}
