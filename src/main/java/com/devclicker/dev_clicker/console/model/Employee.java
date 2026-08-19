package com.devclicker.dev_clicker.console.model;

import com.devclicker.dev_clicker.console.enums.EmployeeType;

public class Employee {
    private EmployeeType employeeType;
    private Long codePerMinute;

    public Employee(EmployeeType employeeType) {
        this.employeeType = employeeType;
        this.codePerMinute = employeeType.getCodePerRound();
    };

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public Long getCodePerMinute() {
        return codePerMinute;
    }

    public void setCodePerMinute(Long codePerMinute) {
        this.codePerMinute = codePerMinute;
    }
}
