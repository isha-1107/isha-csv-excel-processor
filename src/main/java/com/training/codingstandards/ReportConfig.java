package com.training.codingstandards;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReportConfig {

    public static List<Employee> CACHE = new ArrayList<Employee>();

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final String OUTPUT_SHEET = "Payroll";

    public static final String DEFAULT_PASSWORD = System.getenv().getOrDefault("APP_DEFAULT_PASSWORD", "ChangeMe!23");
}
