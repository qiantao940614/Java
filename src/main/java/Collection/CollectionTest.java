package Collection;

import org.junit.Test;

import java.util.*;

public class CollectionTest {
    /**
     *常用的方法
     */
    @Test
    public void testDemo(){
    	//Add
        List list=new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println("List:"+list);
       
        list.remove(1);// remove index =i+1 
        System.out.println("List:"+list);
        //change
        list.set(2,3);
        System.out.println("List:"+list);
        Set set=new HashSet();
        set.add(1);
        set.add(1);
        set.add(null);
        set.add(1);

        System.out.println("Set:"+set+"set.size()"+set.size());
    }

}
