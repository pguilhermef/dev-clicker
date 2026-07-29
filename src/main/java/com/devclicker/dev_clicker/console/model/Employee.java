package com.devclicker.dev_clicker.console.model;

import com.devclicker.dev_clicker.console.enums.EmployeeType;

public class Employee {
    private EmployeeType employeeType;
    private Integer codePerMinute;

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

    public Integer getCodePerMinute() {
        return codePerMinute;
    }

    public void setCodePerMinute(Integer codePerMinute) {
        this.codePerMinute = codePerMinute;
    }
}
