package com.srm.authentication.Entity;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Entity(name = "student_logs")
@Data
public class StudentLogs {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(name = "reg_no")
  String regNo;

  @Column(name = "date")
  LocalDate date;

  @Column(name = "time")
  LocalTime time;
}

