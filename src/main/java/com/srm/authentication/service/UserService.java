package com.srm.authentication.service;

import com.srm.authentication.Entity.Login;
import com.srm.authentication.Entity.LoginResponseDTO;
import com.srm.authentication.Entity.StudentLogs;
import com.srm.authentication.Entity.UniqueEntriesEntity;
import com.srm.authentication.Entity.User;
import java.util.List;

public interface UserService {
  Boolean registerUser(User user);

  LoginResponseDTO loginUser(Login login);

  Boolean saveStudentLogs(String regNo);

  List<StudentLogs> getAllLogs();

  List<UniqueEntriesEntity> getUniqueLogs();
}
