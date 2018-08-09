package Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.junit.Test;

/**
 * Thread的三种实现方式
 * 1 通过类Thread实现
 * 2 通过继承Runnable方法来实现
 * 3 通过Callable和FetureTask来实现
 * @author atpstxq
 *
 */
public class ThreadImpl {
	
	    /* 通过继承Thread来实现Thread*/
	    @Test 
	    public static void ImplByThread(){
	        Thread t1=new Thread(){
	            @Override
	            public void run() {
	                System.out.println("通过继承Thread,重写run方法来实现线程");
	            }
	        };
	        t1.start();
	    }

	    /* 通过继承Runnable方法来实现 */
	    @Test
	    public void ImplByRunnable(){
	       Runnable run=new Runnable() {
	           @Override
	           public void run() {
	               System.out.println("通过Runnable 实现接口");
	           }
	       };
	       Thread t1=new Thread(run);
	       t1.start();
	    }

	    /* 通过Callable和FetureTask来实现 */
	    @Test
	    public void ImplByFetureTask(){
	      Callable cal=new Callable(){
	          @Override
	          public Object call() throws Exception {
	              return "das";
	          }
	      } ;
	      FutureTask futureTask=new FutureTask<>(cal);
	      new Thread(futureTask).start();
	      try{
	           System.out.println(futureTask.get());
	      } catch (InterruptedException e) {
	          e.printStackTrace();
	      } catch (ExecutionException e) {
	          e.printStackTrace();
	      }
	     }
}
