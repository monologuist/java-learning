import com.nyf.dao.AnnotationMapper;
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
import java.util.Date;
import java.util.List;

public class AnnotationTest {

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


    //注解查询
    @Test
    public void test1() {
        SqlSession sqlSession = factory.openSession(true);
        AnnotationMapper mapper = sqlSession.getMapper(AnnotationMapper.class);
        User user = mapper.selectByID(3);
        System.out.println(user);


    }
    //注解更新
    @Test
    public void test2() {
        SqlSession sqlSession = factory.openSession(true);
        AnnotationMapper mapper = sqlSession.getMapper(AnnotationMapper.class);
        User user = mapper.selectByID(1);

        user.setName("特朗普");
        mapper.updateByID(user);
    }

    //注解删除
    @Test
    public void test3() {
        SqlSession sqlSession = factory.openSession(true);
        AnnotationMapper mapper = sqlSession.getMapper(AnnotationMapper.class);
        mapper.deleteByID(30);
        mapper.deleteByID(31);

    }

    //注解查询 多条件
    @Test
    public void test4() {
        SqlSession sqlSession = factory.openSession(true);
        AnnotationMapper mapper = sqlSession.getMapper(AnnotationMapper.class);
        List<User> users = mapper.selectByNameOrSex("武松", "1");
        System.out.println(users);
    }

    //注解插入
    @Test
    public void test5() {
        SqlSession sqlSession = factory.openSession(true);
        AnnotationMapper mapper = sqlSession.getMapper(AnnotationMapper.class);
        User user = new User();
        user.setName("王麻子");
        user.setSex("1");
        user.setBirthday(new Date());
        user.setAddress("王府井");

        mapper.insertUser(user);

        System.out.println("刚刚插入的id为:"+user.getId());

    }

    //复用results
    @Test
    public void test6() {
        SqlSession sqlSession = factory.openSession(true);
        AnnotationMapper mapper = sqlSession.getMapper(AnnotationMapper.class);
        List<User> users = mapper.selectByNameAndID("张三", null);
        System.out.println(users);
    }


    //一对一关联查询
    @Test
    public void test7() {
        SqlSession sqlSession = factory.openSession(true);
        AnnotationMapper mapper = sqlSession.getMapper(AnnotationMapper.class);
        Order order = mapper.selectOrderByID(8);
        System.out.println(order);
    }

    //一对多关联查询
    @Test
    public void test8() {
        SqlSession sqlSession = factory.openSession(true);
        List<Object> objects = sqlSession.selectList("com.nyf.dao.OrderMapper.selectOrderByUserID", 1);
        System.out.println(objects);


        AnnotationMapper mapper = sqlSession.getMapper(AnnotationMapper.class);
        User user = mapper.selectUserByID(2);
        System.out.println(user);
    }
}
