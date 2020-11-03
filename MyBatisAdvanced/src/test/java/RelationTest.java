import com.nyf.dao.OrderMapper;
import com.nyf.dao.UserMapper;
import com.nyf.pojo.Order;
import com.nyf.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class RelationTest {

    private SqlSessionFactory factory;

    @Before
    public void before() throws IOException {
        //工厂构建器
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

        //读取配置文件得到输入流
        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");

        //创建会话工厂
        factory = builder.build(stream);


    }


    // 一对一处理
    @Test
    public void test1()  {
        SqlSession sqlSession = factory.openSession(true);
        //要的是OrderMapper这种类型的代理对象
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        Order order = mapper.selectOrderByID(8);
        System.out.println(order);

    }

    //一对多处理
    @Test
    public void test2() {
        SqlSession sqlSession = factory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectByID(1);
        System.out.println(user);
    }


    //嵌套查询处理方式
    @Test
    public void test3() {
        SqlSession sqlSession = factory.openSession(true);
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        Order order = mapper.selectOrderByID2(8);
        System.out.println(order);
    }


}
