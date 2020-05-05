package com.adda.redis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adda.beans.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long>{

}
