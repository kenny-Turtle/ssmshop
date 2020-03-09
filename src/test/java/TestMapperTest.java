import com.zfj.mapper.CategoryMapper;
import com.zfj.mapper.TestMapper;
import com.zfj.mapper.UserMapper;
import com.zfj.pojo.Category;
import com.zfj.pojo.TestExample;
import com.zfj.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author zfj
 * @create 2020/2/24 13:39
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class TestMapperTest {


    @Autowired
    private TestMapper testMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private UserMapper userMapper;
    @Test
    public void test1(){

        com.zfj.pojo.Test tt = new com.zfj.pojo.Test();
        tt.setId(4);
        tt.setUsername("小杰");
        testMapper.insert(tt);
        System.out.println("添加成功");

    }
    @Test
    public void test02(){
        List<com.zfj.pojo.Test> tests = testMapper.selectByExample();
        System.out.println(
                ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+tests+
                        "<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }
    @Test
    public void test03(){
        com.zfj.pojo.Test test = testMapper.selectByPrimaryKey(2);
        System.out.println(">>>>>>>>>>>>>>"+test+"<<<<<<<<<<<<<<<<<<");
    }
    @Test
    public void test04(){
        Category category = categoryMapper.selectByPrimaryKey(3);
        System.out.println(">>>>>>>>>"+category.getName()+"<<<<<<<<<<<<<<<<<<");
    }
    @Test
    public void test06(){
//        boolean isexit = userService.isExist("张逢杰");
        User user = userMapper.selectByName("张逢杰");
        List<User> users = userMapper.selectByExample();
//        System.out.println(">>>>>>>>>>"+user.getName()+";"+user.getPassword()+"<<<<<<<<");
        System.out.println(users);
    }

}
