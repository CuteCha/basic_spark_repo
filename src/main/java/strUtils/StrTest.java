package strUtils;

import org.junit.Test;

/**
 * Created by cxq on 2018/4/26.
 */
public class StrTest {
    @Test
    public void testTrim() throws Exception{
        String str1 = "abc   ";
        String str2 = "  abc";
        String str3 = "a bc";
        String str4 = " a bc ";
        System.out.println(str1+"|\n"+str1.trim()+"|");
        System.out.println("------------");
        System.out.println(str2+"|\n"+str2.trim()+"|");
        System.out.println("------------");
        System.out.println(str3+"|\n"+str3.trim()+"|");
        System.out.println("------------");
        System.out.println(str4+"|\n"+str4.trim()+"|");
        System.out.println("------------");
    }
}
