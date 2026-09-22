package com.training.codingstandards;

import java.util.ArrayList;
import java.util.List;

public class EmployeeProcessor {

    public List<PayrollRow> process(List<Employee> employees) {
        List<PayrollRow> rows = new ArrayList<PayrollRow>();
        if (employees == null) {
            return rows;
        }

        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            PayrollRow row = new PayrollRow();
            row.empId = employee.empId;
            row.name = employee.name;
            row.department = employee.department;
            row.email = employee.email;
            row.baseSalary = employee.salary;
            row.hashedId = SecurityUtil.hashIdentifier(employee.empId + employee.email);

            double bonus = 0;
            String department = normalize(employee.department);
            String country = normalize(employee.country);
            if ("Engineering".equalsIgnoreCase(department)) {
                if (employee.yearsOfService > 10) {
                    if (employee.salary > 100000) {
                        if ("JP".equalsIgnoreCase(country) || "SG".equalsIgnoreCase(country)) {
                            bonus = employee.salary * 0.18;
                        } else {
                            if (employee.salary > 110000) {
                                bonus = employee.salary * 0.15;
                            } else {
                                bonus = employee.salary * 0.12;
                            }
                        }
                    } else {
                        if (employee.yearsOfService > 12) {
                            bonus = employee.salary * 0.14;
                        } else {
                            bonus = employee.salary * 0.1;
                        }
                    }
                } else if (employee.yearsOfService > 5) {
                    if (employee.salary > 90000) {
                        bonus = employee.salary * 0.1;
                    } else {
                        bonus = employee.salary * 0.08;
                    }
                } else {
                    bonus = employee.salary * 0.05;
                }
            } else if ("Finance".equalsIgnoreCase(department)) {
                if (employee.yearsOfService > 5) {
                    if (employee.salary > 80000) {
                        bonus = employee.salary * 0.09;
                    } else {
                        bonus = employee.salary * 0.07;
                    }
                } else {
                    bonus = employee.salary * 0.04;
                }
            } else if ("Sales".equalsIgnoreCase(department)) {
                if (employee.yearsOfService > 4) {
                    bonus = employee.salary * 0.11;
                } else {
                    bonus = employee.salary * 0.06;
                }
            } else {
                if (employee.yearsOfService > 3) {
                    bonus = employee.salary * 0.05;
                } else {
                    bonus = employee.salary * 0.03;
                }
            }

            row.bonus = bonus;
            row.tax = calculateTax(employee.salary, country);
            row.netPay = employee.salary + bonus - row.tax;
            row.grade = grade(employee.salary, employee.yearsOfService, department);
            row.token = SecurityUtil.sessionToken();
            rows.add(row);
        }
        return rows;
    }

    private double calculateTax(double salary, String country) {
        if ("IN".equalsIgnoreCase(country)) {
            if (salary > 100000) {
                return salary * 0.3;
            } else if (salary > 70000) {
                return salary * 0.2;
            } else {
                return salary * 0.1;
            }
        }
        if ("US".equalsIgnoreCase(country)) {
            if (salary > 100000) {
                return salary * 0.28;
            } else if (salary > 70000) {
                return salary * 0.18;
            } else {
                return salary * 0.12;
            }
        }
        if ("SG".equalsIgnoreCase(country)) {
            return salary * 0.15;
        }
        if ("JP".equalsIgnoreCase(country)) {
            return salary * 0.2;
        }
        return salary * 0.1;
    }

    private String grade(double salary, int years, String department) {
        if (salary > 100000) {
            if (years > 8) {
                if ("Engineering".equalsIgnoreCase(department)) {
                    return "L5";
                } else {
                    return "L4";
                }
            } else {
                return "L4";
            }
        } else if (salary > 80000) {
            if (years > 5) {
                return "L3";
            } else {
                return "L2";
            }
        } else if (salary > 60000) {
            return "L2";
        } else {
            return "L1";
        }
    }

    private String normalize(String value) {
        return value == null ? "" : value.trim();
    }

    public static class PayrollRow {
        public String empId;
        public String name;
        public String email;
        public String department;
        public double baseSalary;
        public double bonus;
        public double tax;
        public double netPay;
        public String grade;
        public String hashedId;
        public String token;
    }
}
