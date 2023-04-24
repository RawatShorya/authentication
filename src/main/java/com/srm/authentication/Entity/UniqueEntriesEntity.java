package com.srm.authentication.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Entity(name = "data_processing")
@Data
public class UniqueEntriesEntity {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @Column(name = "reg_no")
  String regNo;

  @Column(name = "count")
  int count;
}
