package com.reactapi.repository;

import com.reactapi.entity.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepo extends CrudRepository<Department, Integer> {
}
