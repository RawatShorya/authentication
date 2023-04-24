package com.srm.authentication.repository;

import com.srm.authentication.Entity.StudentLogs;
import com.srm.authentication.Entity.UniqueEntityDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentLogsRepository extends JpaRepository<StudentLogs, Long> {

  @Query(
      value = "SELECT reg_no as reg_no, COUNT(*) as count FROM student_logs GROUP BY reg_no",
      nativeQuery = true)
  List<UniqueEntityDTO> findUnique();
}
