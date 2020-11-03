import com.nyf.pojo.Products;
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

public class MyTest {
    private  SqlSessionFactory factory;

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
    public void TestSelectById() throws IOException {
//        //获取的工厂构造器
//        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//        //加载配置文件
//        InputStream stream = Resources.getResourceAsStream("mybatis-config.xml");
//        //获得会话工厂
//        SqlSessionFactory factory = builder.build(stream);
        //获得会话
        SqlSession sqlSession = factory.openSession();

        //执行sql
        Products product = sqlSession.selectOne("selectProductById", 1);
        System.out.println(product);
    }

    //删除
    @Test
    public void TestDelete(){
        //获取会话  可设置是否自动提交
        SqlSession sqlSession = factory.openSession(true);
        //所有的delete update insert 返回的都是操作成功的记录数量
        int deleteById = sqlSession.delete("deleteById",1);
        if (deleteById>0){
            System.out.println("删除成功");
        }
        //默认不自动提交 如果是写操作则需要手动提交
//        sqlSession.commit();
    }

    //更新
    @Test
    public void TestUpdate(){
        //获取会话
        SqlSession sqlSession = factory.openSession(true);
        //查询一个原有的数据
        Products product = sqlSession.selectOne("selectProductById",3);
        System.out.println(product);
        //更新某些属性
        product.setName("山东大葱");
        product.setPrice(6.9);
        //执行更新操作
        int count = sqlSession.update("updateById",product);
        if (count>0){
            System.out.println("更新成功");
        }
    }

    //添加
    @Test
    public void TestInsert(){
        //获取会话
        SqlSession sqlSession = factory.openSession(true);

        Products product = new Products();
        product.setName("美味的新疆大葡萄干");
        product.setPrice(86.4);
        product.setPdate(new Date());
        product.setCid("s001");

        sqlSession.insert("insertProduct",product);
        System.out.println("插入的id为:"+product.getPid());
    }

    //查询整个列表
    @Test
    public void TestSelectAll() {
        //获取会话
        SqlSession sqlSession = factory.openSession(true);
        List<Products> products = sqlSession.selectList("selectAllProduct");
        System.out.println(products);
    }

    //模糊查询
    @Test
    public void TestSelect() {
        //获取会话
        SqlSession sqlSession = factory.openSession(true);
        List<Products> products = sqlSession.selectList("searchByKey", "新疆");
        System.out.println(products);
    }
}
