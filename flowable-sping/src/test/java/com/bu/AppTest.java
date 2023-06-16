package com.bu;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.flowable.engine.HistoryService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
/**
 * Unit test for simple App.
 */ public class AppTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }

    @Autowired
    private TaskService taskService;

    @org.junit.jupiter.api.Test
    public void tsList() {
        Task bhz = taskService.createTaskQuery().taskId("bhz").singleResult();
        System.out.println("bhz = " + bhz);
    }

}
