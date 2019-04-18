package com.bear;

import com.bear.admin.StartAdmin;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by mby on 2019/4/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartAdmin.class)
@ActiveProfiles("dev")
public class BaseTest {
}
