package com.leonardo.demo.repositories;

import com.leonardo.demo.models.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser_Id(Long Id);

}
