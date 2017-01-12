import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * Created by ihb on 11.01.17.
 */
public class TestCollections {

    private ArrayList<Integer> list = new ArrayList<>();
    private ArrayList<Integer> list2 = new ArrayList<>();
    private TreeSet<Integer> set = new TreeSet<>();
    private TreeSet<Integer> set2 = new TreeSet<>();
    private HashMap<String, String> map = new HashMap<>();

    @After
    public void afterTest(){
        list.clear();
        list2.clear();
        set.clear();
        set2.clear();
        map.clear();
    }



    // ARRAYLIST
    @Test
    public void testListSize() {
        list.add(1);
        list.add(2);
        list.add(3);
        Assert.assertTrue(list.size() == 3);
    }

    @Test
    public void testListAdd() {
        list.add(1);
        list.add(2);
        list.add(3);
        Assert.assertTrue(list.size() == 3);
    }

    @Test
    public void testListGet() {
        list.add(1);
        list.add(2);
        list.add(3);
        Assert.assertTrue(list.get(1) == 2);
    }

    @Test
    public void testListIterator() {
        list.add(1);
        list.add(2);
        list.add(3);
        Assert.assertTrue(list.iterator().next() == 1);
    }

    @Test
    public void testListEquals() {
        list.add(1);
        list.add(2);
        list.add(3);
        list2.add(1);
        list2.add(2);
        list2.add(3);
        Assert.assertTrue(list.equals(list2));
    }

    @Test
    public void testListEmpty() {
        list.add(1);
        list.add(2);
        Assert.assertTrue(!list.isEmpty());
    }

    @Test
    public void testListAddIndex() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(0, 5);
        Assert.assertTrue(list.iterator().next().equals(5));
    }

    @Test
    public void testListRemove() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.remove(2);
        Assert.assertTrue(list.size() == 2);
    }

    @Test
    public void testListToString() {
        list.add(1);
        list.add(2);
        list.add(3);
        Assert.assertTrue(list.toString().equals("[1, 2, 3]"));
    }

    @Test
    public void testListSet() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.set(2, 1);
        Assert.assertTrue(list.listIterator(2).next().equals(1));
    }

    @Test
    public void testListClear() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.clear();
        Assert.assertTrue(list.size() == 0);
    }











    // TREE SET


    @Test
    public void testSetSize() {
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        Assert.assertTrue(set.size() == 4);
    }

    @Test
    public void testSetAdd() {
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(3);
        Assert.assertTrue(set.size() == 3);
    }

    @Test
    public void testSetIterator() {
        set.add(1);
        set.add(2);
        set.add(3);
        Assert.assertTrue(set.iterator().next() == 1);
    }

    @Test
    public void testSetEquals() {
        set.add(1);
        set.add(2);
        set.add(3);
        set2.add(1);
        set2.add(2);
        set2.add(3);
        Assert.assertTrue(set.equals(set2));
    }

    @Test
    public void testSetEmpty() {
        set.add(1);
        set.add(2);
        set.add(3);
        Assert.assertTrue(!set.isEmpty());
    }

    @Test
    public void testSetRemove() {
        set.add(1);
        set.add(2);
        set.add(3);
        set.remove(1);
        Assert.assertTrue(set.size() == 2);
    }

    @Test
    public void testSetToString() {
        set.add(1);
        set.add(2);
        set.add(3);
        Assert.assertTrue(set.toString().equals("[1, 2, 3]"));
    }

    @Test
    public void testContains() {
        set.add(1);
        set.add(2);
        set.add(3);
        Assert.assertTrue(!set.contains(4));
    }

    @Test
    public void testSetClear() {
        set.add(1);
        set.add(2);
        set.add(3);
        set.clear();
        Assert.assertTrue(set.size() == 0);
    }




    //TREEMAP


    @Test
    public void testMapSize() {

        map.put("Hello", "Java");
        map.put("Goodbye", "Genius");
        map.put("Bie", "Poe");
        map.put("Rose", "Gone");
        map.put("Pinky", "Brain");
        Assert.assertTrue(map.size() == 5);
    }

    @Test
    public void testMapRemove() {
        map.put("Hello", "Java");
        map.put("Goodbye", "Genius");
        map.put("Bie", "Poe");
        map.put("Rose", "Gone");
        map.remove("Hello");
        Assert.assertTrue(map.size() == 3);
    }

    @Test
    public void testMapGet() {
        map.put("Hello", "Java");
        map.put("Goodbye", "Genius");
        map.put("Bie", "Poe");
        map.put("Rose", "Gone");
        Assert.assertTrue(map.get("Bie").equals("Poe"));
    }

    @Test
    public void testMapContains() {
        map.put("Hello", "Java");
        map.put("Goodbye", "Genius");
        map.put("Bie", "Poe");
        map.put("Rose", "Gone");
        Assert.assertTrue(map.containsKey("Rose"));
        Assert.assertFalse(map.containsKey("jfose"));
    }


    @Test
    public void testMapPut() {
        map.put("Hello", "Java");
        map.put("Goodbye", "Genius");
        map.put("Bie", "Poe");
        map.put("Rose", "Gone");
        map.put("Baby", "Right");
        Assert.assertTrue(map.size() == 5);
    }

    @Test
    public void testMapReplace() {
        map.put("Hello", "Java");
        map.put("Goodbye", "Genius");
        map.put("Bie", "Poe");
        map.put("Rose", "Gone");
        map.put("Baby", "Right");
        map.replace("Goodbye", "Genius", "C++");
        Assert.assertTrue("C++".equals(map.get("Goodbye")));
    }

    @Test
    public void testMapClear() {
        map.put("Hello", "Java");
        map.put("Goodbye", "Genius");
        map.put("Bie", "Poe");
        map.put("Rose", "Gone");
        map.put("Baby", "Right");
        map.clear();
        Assert.assertTrue(map.size() == 0);
    }
}
