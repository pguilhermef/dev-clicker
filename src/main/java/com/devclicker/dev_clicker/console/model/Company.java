package com.devclicker.dev_clicker.console.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Company {
    String name;
    Date startDate;
    Long code;
    BigDecimal money;
    List <Employee> employees;
    List <Product> products;
}
