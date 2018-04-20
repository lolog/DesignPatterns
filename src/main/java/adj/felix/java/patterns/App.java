package adj.felix.java.patterns;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Integer i0 = new Integer(0);
        Integer i00 = Integer.valueOf(0);
        System.out.println(i0);
        System.out.println(i00);
        System.out.println(i0 == i00);
        
        new Integer(200);
        Integer.valueOf(200);
    }
}
