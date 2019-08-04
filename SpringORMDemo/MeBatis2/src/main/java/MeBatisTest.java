import com.hongdu.gupao.mebatis.*;

/**
 * @ClassName MeBatisTest
 * @Description TODO
 * @Author dudu
 * @Date 2019/8/4 15:39
 * @Version 1.0
 */
public class MeBatisTest {

    public static void main(String[] args) {
        //获取一个sqlsession
        HdSqlSession sqlSession = new HdSqlSession(new HdConfiguration(), new HdExecutor());
        //获取代理对象 : 传入 接口类 --》 就是为了完成jdk的代理参数传递
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = mapper.selectBlogById(1);
        System.out.println(blog);
    }
}
