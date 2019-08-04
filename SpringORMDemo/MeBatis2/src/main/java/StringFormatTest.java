/**
 * @ClassName StringFormatTest
 * @Description TODO
 * @Author dudu
 * @Date 2019/8/4 17:01
 * @Version 1.0
 */
public class StringFormatTest {

    public static void main(String[] args) {
        String s = "select * from blog where bid = %d";
        Integer id = 1;
        String ss = String.format(s, id);
        System.out.println(ss);
    }
}
