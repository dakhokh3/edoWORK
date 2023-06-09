package com.education.service.employee;

import model.dto.EmployeeDto;

import java.util.Collection;

/**
 * @author Kryukov Andrey
 * Интерфейс employee сервиса
 */
public interface EmployeeService {
    EmployeeDto save(EmployeeDto employeeDto);

    Collection<EmployeeDto> findAllByFullName(String fullName);
}
