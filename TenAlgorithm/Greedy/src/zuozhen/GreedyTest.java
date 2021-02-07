package zuozhen;

import com.sun.deploy.security.SelectableSecurityManager;

import java.beans.beancontext.BeanContext;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 区域覆盖问题
 */
public class GreedyTest {
    public static void main(String[] args) {
        //数据初始化
        //电台覆盖城市
        HashMap<String, HashSet<String>> broadCasts = new HashMap<>();
        //电台
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        broadCasts.put("K1", hashSet1);
        broadCasts.put("K2", hashSet2);
        broadCasts.put("K3", hashSet3);
        broadCasts.put("K4", hashSet4);
        broadCasts.put("K5", hashSet5);
        //所有城市
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");
        allAreas.add("深圳");
        //存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();
        //临时集合，存放电台覆盖地区和当前还没有覆盖地区的交集
        HashSet<String> temp = new HashSet<>();
        while (allAreas.size() != 0) {
            String maxKey = null;//保存在遍历过程中能够覆盖最多未覆盖地区对应的电台的key
            for (String key : broadCasts.keySet()) {
                temp.clear();
                HashSet<String> hashSet = broadCasts.get(key);
                temp.addAll(hashSet);
                temp.retainAll(allAreas);
                if (temp.size() > 0 && (maxKey == null || temp.size() > broadCasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            if (maxKey != null) {
                selects.add(maxKey);
                allAreas.removeAll(broadCasts.get(maxKey));
            }
        }
        System.out.println(selects);
    }
}
