public class JVMTest {
    public static void main(String[] args) {
        String s1 = "asdf";
        String s2 = new String("asdf");
        String s3 = "aaaa";

        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
        System.out.println(s3.hashCode());

        //intern() -> runtime constant pool에 존재하는 값을 가져옴.
        System.out.println(s1.intern());
        System.out.println(s2.intern());

        if(s1.equals(s2))
            System.out.println(true);
        else
            System.out.println(false);

        if(s1.hashCode() == s2.hashCode())
            System.out.println(true);
        else
            System.out.println(false);
    }
}
