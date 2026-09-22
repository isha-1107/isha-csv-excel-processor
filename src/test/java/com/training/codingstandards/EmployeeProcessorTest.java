package com.training.codingstandards;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeProcessorTest {

    @Test
    void processCreatesPayrollRowForEachEmployee() {
        Employee employee = new Employee("1001", "Asha Raman", "asha.raman@example.com",
                "Engineering", 92000, 6, "IN", "lead.eng@example.com");

        EmployeeProcessor processor = new EmployeeProcessor();
        List<EmployeeProcessor.PayrollRow> rows = processor.process(Arrays.asList(employee));

        assertNotNull(rows);
        assertEquals(1, rows.size());
        assertEquals("1001", rows.get(0).empId);
        assertEquals("Engineering", rows.get(0).department);
    }

    @Test
    void processUsesStringValueChecksForPayrollRules() {
        Employee employee = new Employee("1001", "Asha Raman", "asha.raman@example.com",
                new String("Engineering"), 92000, 6, new String("IN"), "lead.eng@example.com");

        EmployeeProcessor processor = new EmployeeProcessor();
        List<EmployeeProcessor.PayrollRow> rows = processor.process(Arrays.asList(employee));

        assertNotNull(rows);
        assertEquals(1, rows.size());
        assertEquals(9200.0, rows.get(0).bonus, 0.0001);
        assertEquals(18400.0, rows.get(0).tax, 0.0001);
    }

    @Test
    void processReturnsEmptyListForNullInput() {
        EmployeeProcessor processor = new EmployeeProcessor();

        assertNotNull(processor.process(null));
        assertTrue(processor.process(null).isEmpty());
    }
}
