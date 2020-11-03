import com.ahn.dao.KuserMapper;
import com.ahn.pojo.Kuser;
import com.ahn.pojo.KuserExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class GeneratorTest {

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


    //简单的不带条件的CURD
    @Test
    public void test(){
        SqlSession sqlSession = factory.openSession(true);
        KuserMapper mapper = sqlSession.getMapper(KuserMapper.class);

        //查询某个id
        Kuser kuser = mapper.selectByPrimaryKey(6);
        System.out.println(kuser);

        //查询全部
        //Example 封装的条件处理对象 本质就是根据条件  生成sql语句
        //现在传入的是null，没有条件，那就是查询全部
//        List<Kuser> kusers = mapper.selectByExample(null);
//        System.out.println(kusers);


        //添加
//        Kuser kuser = new Kuser();
//        kuser.setUsername("李四");
//        kuser.setAddress("大理");
//        kuser.setSex("1");
//
//        int insert = mapper.insert(kuser);
//        System.out.println(insert);

        //更新
//        Kuser kuser1 = mapper.selectByPrimaryKey(1);
//        kuser1.setUsername("none");
//        mapper.updateByPrimaryKey(kuser1);
//
//        //删除
//        mapper.deleteByPrimaryKey(1);
    }




    //带条件的CURD
    @Test
    public void test1() {
        SqlSession sqlSession = factory.openSession(true);
        KuserMapper mapper = sqlSession.getMapper(KuserMapper.class);

        //构造条件对象
        KuserExample kuserExample = new KuserExample();
        //添加一个条件 需要明确时and还是or Criteria是标准；条件的意思
        KuserExample.Criteria criteria = kuserExample.createCriteria();
        //输入criteria.andId，出现一堆方法，就选需要的操作对应的英文单词即可
        criteria.andIdGreaterThanOrEqualTo(5);
//        criteria.andAddressEqualTo("河北清河县");


        //这样就既有and又有or，直接拿条件对象去查询就可以
        KuserExample.Criteria or = kuserExample.or();
        or.andAddressEqualTo("河北清河县");


        //查询id大于10的
        List<Kuser> kusers = mapper.selectByExample(kuserExample);
        System.out.println(kusers);

    }



}
