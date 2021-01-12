public class SomeBean {
    @AutoInjectable
    private SomeInterface fld1;
    @AutoInjectable
    private SomeOtherInterface fld2;
    public void foo(){
        fld1.doSomething();
        fld2.doSomething();
    }
}