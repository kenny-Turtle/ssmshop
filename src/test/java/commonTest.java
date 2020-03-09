import com.zfj.mapper.UserMapper;
import com.zfj.pojo.User;
import com.zfj.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author zfj
 * @create 2020/2/25 13:58
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class commonTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test05(){

        System.out.println(1<=0);
    }
    @Test
    public void test06(){
        boolean xz = userService.isExist("小朱");

//        System.out.println(">>>>>>>>>>"+user.getName()+";"+user.getPassword()+"<<<<<<<<");
    }
}
