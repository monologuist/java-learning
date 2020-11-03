import com.nyf.dao.ProductDao;
import com.nyf.pojo.Products;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MyTest {

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

    //根据id查询
    @Test
    public void test1()  {
        SqlSession sqlSession = factory.openSession(true);
        //获取代理对象,指定接口 mybatis会自动根据接口产生一个类,并创建对象返回
        //（你要的代理对象长啥样，就长ProductDao这个接口这样，球球你给我吧）
        ProductDao mapper = sqlSession.getMapper(ProductDao.class);
        //直接调用方法来完成CRUD
        Products product = mapper.selectByID(3);
        System.out.println(product);

    }


    //根据id或名称查询-使用动态sql查询方式更灵活
    @Test
    public void test2() {
        SqlSession sqlSession = factory.openSession(true);
        ProductDao mapper = sqlSession.getMapper(ProductDao.class);

        //封装查询条件的对象
        Products product = new Products();
        product.setName("老干妈");
//        product.setPid(3);
        //用where标签去掉第一个and
        List<Products> products = mapper.selectByIDorName(product);

        System.out.println(products);
    }

    //查询多个id-使用动态sql查询方式更灵活
    @Test
    public void test3() {
        SqlSession sqlSession = factory.openSession(true);
        ProductDao mapper = sqlSession.getMapper(ProductDao.class);

        List<Integer> ids = new ArrayList<>();
        ids.add(3);
        ids.add(2);
        List<Products> products = mapper.selectProductByIDS(ids);

        System.out.println(products);
    }

    //根据id更新-使用动态sql查询方式更灵活
    @Test
    public void test4() {
        SqlSession sqlSession = factory.openSession(true);
        ProductDao mapper = sqlSession.getMapper(ProductDao.class);
        //用对象来包装要更新的数据
        Products products = mapper.selectByID(8);
        products.setName("张三疯");
        products.setPrice(9999.9);
        //执行更新
        int count = mapper.updateProductTest(products);
        System.out.println(count);
        System.out.println(products);
    }

}
