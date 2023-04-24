package com.srm.authentication.service.impl;

import com.srm.authentication.Entity.Login;
import com.srm.authentication.Entity.LoginResponseDTO;
import com.srm.authentication.Entity.StudentLogs;
import com.srm.authentication.Entity.UniqueEntityDTO;
import com.srm.authentication.Entity.UniqueEntriesEntity;
import com.srm.authentication.Entity.User;
import com.srm.authentication.repository.StudentLogsRepository;
import com.srm.authentication.repository.UniqueEntriesEntityRepo;
import com.srm.authentication.repository.UserRepository;
import com.srm.authentication.service.UserService;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

  @Autowired UserRepository userRepository;
  @Autowired StudentLogsRepository studentLogsRepository;
  @Autowired UniqueEntriesEntityRepo uniqueEntriesEntityRepo;

  @Override
  public Boolean registerUser(User user) {
    User newUser = new User();
    BeanUtils.copyProperties(user, newUser);
    userRepository.save(newUser);
    return true;
  }

  @Override
  public LoginResponseDTO loginUser(Login login) {
    User loginUser = userRepository.findByEmail(login.getEmail());
    LoginResponseDTO loginDTO = new LoginResponseDTO();
    if (login.getPassword().equals(loginUser.getPassword())) {
      loginDTO.setAcceptance(true);
      loginDTO.setRole_type(Math.toIntExact(loginUser.getRoleType()));
      return loginDTO;
    }
    loginDTO.setAcceptance(false);
    loginDTO.setRole_type(0);
    return loginDTO;
  }

  @Override
  public Boolean saveStudentLogs(String regNo) {
    StudentLogs studentLogs = new StudentLogs();
    studentLogs.setRegNo(regNo);
    studentLogs.setDate(LocalDate.now());
    studentLogs.setTime(LocalTime.now());
    studentLogsRepository.save(studentLogs);
    List<StudentLogs> allStudentLogs = studentLogsRepository.findAll();
    List<UniqueEntityDTO> uniqueLogsEntity = findAllUnique(allStudentLogs);
    uniqueEntriesEntityRepo.deleteAll();
    for (int i = 0; i < uniqueLogsEntity.size(); i++) {
      UniqueEntriesEntity uniqueEntriesEntity = new UniqueEntriesEntity();
      uniqueEntriesEntity.setRegNo(uniqueLogsEntity.get(i).getReg_no());
      uniqueEntriesEntity.setCount(uniqueLogsEntity.get(i).getCount());
      uniqueEntriesEntityRepo.save(uniqueEntriesEntity);
    }
    return true;
  }

  @Override
  public List<StudentLogs> getAllLogs() {
    List<StudentLogs> studentLogsList = studentLogsRepository.findAll(Sort.by("id").descending());
    return studentLogsList;
  }

  @Override
  public List<UniqueEntriesEntity> getUniqueLogs() {
    List<UniqueEntriesEntity> uniqueEntriesEntityList = uniqueEntriesEntityRepo.findAll();
    return uniqueEntriesEntityList;
  }

  private List<UniqueEntityDTO> findAllUnique(List<StudentLogs> allStudentLogs) {
    Map<String, Integer> regNoCountMap = new HashMap<>();
    for (StudentLogs studentLogs : allStudentLogs) {
      String regNo = studentLogs.getRegNo();
      if (regNoCountMap.containsKey(regNo)) {
        regNoCountMap.put(regNo, regNoCountMap.get(regNo) + 1);
      } else {
        regNoCountMap.put(regNo, 1);
      }
    }
    List<UniqueEntityDTO> uniqueEntityDTO = new ArrayList<>();
    for (Map.Entry<String, Integer> entry : regNoCountMap.entrySet()) {
      String key = entry.getKey();
      Integer value = entry.getValue();
      UniqueEntityDTO uniqueEntityDTO1 = new UniqueEntityDTO();
      uniqueEntityDTO1.setReg_no(key);
      uniqueEntityDTO1.setCount(value);
      uniqueEntityDTO.add(uniqueEntityDTO1);
    }
    return uniqueEntityDTO;
  }
}
