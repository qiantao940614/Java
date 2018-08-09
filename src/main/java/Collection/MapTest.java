package Collection;

import org.junit.Test;

import java.util.*;


public class MapTest{
    @Test
    public void testDemo(){
        Map map=new HashMap();
        System.out.println(map.containsKey(1));
        Hashtable hashtable=new Hashtable();
        Collections.synchronizedMap(map);

    }
    /**
     * HashMap的四种遍历方式
     */
    @Test
    public void eachHashMap(){
        Map map=new HashMap();
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("1", "1");
        hashMap.put("2", "2");
        Set<Map.Entry<String, String>> entrySet = hashMap.entrySet();
        //1.迭代器实现 效率高
        Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();
        while(iterator.hasNext()){
            Map.Entry<String, String> entry = iterator.next();
            System.out.println("1:      key="+entry.getKey()+"      value="+entry.getValue());
        }
        //2.entrySet
        for (Map.Entry<String, String> entry : entrySet) {
            System.out.println("2:      key="+entry.getKey()+"      value="+entry.getValue());
        }
        //3.keySet
        Set<String> keySet = hashMap.keySet();
        for (String entry : keySet) {
            System.out.println("3:      key="+entry+"      value="+hashMap.get(entry));
        }
        //java8的forEach方法
        hashMap.forEach((key,value)->{System.out.println("4:      key="+key+"      value="+value);});
    }

}
