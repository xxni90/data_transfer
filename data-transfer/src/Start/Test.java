package Start;

public class Test {

    public static void main(String[] args) {
//    [a-z0-9A-Z]
//    [\u4e00-\u9fa5]
//    [,\.;\:"'!] "

        String a = "的撒加ajdkshfkasdfsjdf;a空间32478034898934啊浪费大花洒发动机撒地方；撒的路口附近sdkfjsdfhsadfsadhkfsadfldjshfjkasldfsdfh罚款华;;；！。，,，“”‘’盛顿‘’''   ";
        System.out.println(a.matches("[a-z0-9A-Z\u4e00-\u9fa5,\\.;\\:'!；。.！，“”‘’\\s]*"));
    }
}
