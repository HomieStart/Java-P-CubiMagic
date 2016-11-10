package core.Hilos;

/**
 * @title Java-Threads-TEST
 * @author TR-X Homie        
 **/

public class TestThread {
    public static void main(String[] args) {
        TestWithMethods Test = new TestWithMethods("Hello Thread");
        
        ThreadAnon A = new ThreadAnon(Test, "SimpleMethod1", null);
        A.GetThread().run();
        while(true){
            
        }
    }
}

class TestWithMethods{
    private String SayAnyThing;
    
    public TestWithMethods(String any){
        this.SayAnyThing = any;
    }
    
    public void setShowString(String say){
        this.SayAnyThing = say;
    }
    
    public void getShowString(String say){
        this.SayAnyThing = say;
    }
    
    public void SimpleMethod1(){
        System.out.println("Method1: " + this.SayAnyThing);
    }
    public void SimpleMethod2(){
        System.out.println("Method2: " + this.SayAnyThing);
    }
    public void SimpleMethod3(){
        System.out.println("Method3: " + this.SayAnyThing);
    }
}
