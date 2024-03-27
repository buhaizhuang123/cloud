package com.bu;

import com.bu.fourLevel.dao.EngFourLevelRepository;
import com.bu.fourLevel.dto.EngFourLeverDto;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.flowable.engine.HistoryService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
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

    @Resource
    private EngFourLevelRepository repository;

    @org.junit.jupiter.api.Test
    public void ts01() {
        List<EngFourLeverDto> all = repository.findAll();
        System.out.println(all);
    }


}
