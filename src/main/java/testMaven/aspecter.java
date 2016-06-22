package testMaven;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.Before;

@Aspect
public class aspecter {

	public aspecter(){



	}

	public static int c;
	public int d;
	public static String a;

	public static int getC() {
		return c;
	}

	public static void setC(int c) {
		aspecter.c = c;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public static String getA() {
		return a;
	}

	public static void setA(String a) {
		aspecter.a = a;
	}

	boolean qwe = false;
	int ewq = 5;


	static boolean tracked = true;

	public static boolean isTracked() {
		return tracked;
	}

	public static void setTracked(boolean tracked) {
		aspecter.tracked = tracked;
	}

	@Pointcut("if()")
	public static boolean tracked() {
	  return tracked;
	}

	@Before("execution(*  testMaven.testing.aa(..)) ")
	public void testBefore(){
		System.out.println(tracked);
		if(isTracked() == true){
		    System.out.println("yooi");
		}
	}
	
	@After("execution(*  testMaven.testing.aa(..)) && tracked()")
	public void testBefore3(){
	    System.out.println("yooi3");
	    tracked = true;
	}

	@Before("execution(*  testMaven.testing.setDd(..)) && tracked() ")
	public void testBefore2(){
	    System.out.println("yooi2");
	}

	//	@After("execution (*  testMaven.testing.getDd())")
	//	public void getter(){
	//		System.out.println("after get the getDd");
	//		
	//	}

	@Pointcut("execution (*  testMaven.tester.setZ(..))")
	public void setterZ(){}

	@Before("setterZ()")
	public void settingZ(){
		System.out.println("before set Z");
	}


	//	@Before("initialization(testMaven.testing.new(..))")
	//	public void createClass(){
	//		System.out.println("create testing class 2");
	//	}

//	@Before("get (int testMaven.testing.dd)")
//	public void getD2(){
//		System.out.println("dd is accessed 2");
//		//		System.out.println("value of dd " + dd);
//	}

	@Pointcut("call (*  testMaven.testing.getDd(..))")
	public void getter(){}

	@AfterReturning(pointcut="execution(* testMaven.testing.getDd(..)) && tracked()", returning="returned")
	public void testGet(long returned){
		System.out.println("get ok");
		System.out.println("returned value = " + returned);
	}


	//	@Pointcut("getter() && cflow(setterZ())")
	//	public void getterSetter(){}

	//	@After("getterSetter()")
	//	public void testinger(){
	//		System.out.println("ok");
	//	}

	//	   @After("execution(testMaven.testing.new(..))")
	//	    public void constructorInvocation(JoinPoint joinPoint)
	//	            throws Throwable {
	////	        testing instance = (testing) joinPoint.getTarget();
	//	        System.out.println(joinPoint.getTarget());
	////	        instance.aa(221);;
	//	    }



	//	
	//	
	//	
	//	@Pointcut("call (*  testMaven.testing.setDd(..))")
	//	public void setter(){}
	//	
	//	@After("setter()")
	//	public void testSet(){
	//		System.out.println("set ok");
	//	}
	//	
	//	@Pointcut("getter() && setter()")
	//	public void getterSetter(){}
	//	
	//	@After("getterSetter()")
	//	public void testerd(){
	//		System.out.println("works");
	//	}

	//	public void getterSetter(){}
	//	
	//	@After("getterSetter()")
	//	public void testering(){
	//		System.out.println("execute after that happens");
	//	}

	//	@After("setter() && getter()")


	//	pointcut getD() : get(int testMaven.testing.d);
	//	
	//	before(): getD(){
	//		System.out.println("D is accessed");
	//	}


	//pointcut test(): initialization(testMaven.testing.new(..));
	//	
	//	before(): test(){
	//		System.out.println("create testing class");
	//	}
}
