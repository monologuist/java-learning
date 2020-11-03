package com.nyf.dao;

import com.nyf.pojo.Order;
import com.nyf.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AnnotationMapper {

    //sql就写在注解上 不用编写mapper文件
    //这样便定义好了一个id为pname_map的Results自定义映射关系，在别的接口处直接引用即可（当然了你为什么不直接按照表字段把类建好呢？
    @Select("select * from kuser where id = #{id}")
    @Results(id = "pname_map",
            value = {
            @Result(column = "username",property = "name"),
            @Result(column = "id",property = "id")}
            )
    public User selectByID(int id);

    @Update("update kuser set username = #{name},sex = #{sex},address = #{address} where id = #{id}")
    @ResultMap("pname_map")
    public void updateByID(User user);

    @Delete("delete from kuser where id = #{id}")
    public void deleteByID(int id);


    //根据名称或者性别查询
    @Select("select * from kuser where username = #{name} or sex = #{sex}")
    @Results(
            @Result(column = "username",property = "name")
    )
    public List<User> selectByNameOrSex(@Param("name") String username, @Param("sex") String sex);


    //添加数据
    @Insert("insert into kuser values(null,#{name},#{birthday},#{sex},#{address})")
    //获取自增的主键值
//    before=false：由于mysql支持自增长主键，所以先执行插入语句，再获取自增长主键值。
//    keyColumn：自增长主键的字段名
//    keyProperty: 实体类对应存放字段，注意数据类型和resultType一致
//    statement：实际执行的sql语句
//    SelectKey返回的值存在实体类中，线程安全，所以不论插入成功与否id都会安全自增
    // 在执行插入了之后去查id然后给他，而不是之前，所以before = false
    @SelectKey(statement = "SELECT LAST_INSERT_ID() AS id",keyColumn = "id",resultType = Integer.class,keyProperty = "id", before = false)
    public void insertUser(User user);



    //动态sql  需求是根据name或者id查询 需要判断参数是否有效
    @Select("<script>" +
            "select * from kuser" +
            "<where>" +
            "<if test='name != null'>" +
            "and username like '%${name}%'" +
            "</if>" +
            "<if test='id != null'>" +
            "and id = #{id}" +
            "</if>" +
            "</where>" +
            "</script>")
    @ResultMap("pname_map")
    public List<User> selectByNameAndID(@Param("name") String name,@Param("id") Integer id);




    //查询订单时同时查询用户信息
    @Select("select * from orders where id = #{id}")
    @Results(
            id ="order_map",
            value = {
            @Result(column = "user_id",property = "user",
                    javaType = User.class,
                    one = @One(select = "com.nyf.dao.OrderMapper.selectUserByID")),
            @Result(column = "username",property = "name")}
    )
    public Order selectOrderByID(int id);


    //根据id查询用户 同时查询订单信息
    @Select("select * from kuser where id = #{id}")
    @Results(
            value = {
                    @Result(column = "username",property = "name"),
                    @Result(column = "id",property = "id"),
                    @Result(column = "id", property = "orders",
                            many = @Many(select = "com.nyf.dao.OrderMapper.selectOrderByUserID"))
            }
    )
    public User selectUserByID(int id);




}
