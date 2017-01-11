package lab3;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.util.*;

/**
 * Created by ihb on 08.12.16.
 */
public class MyThread extends Thread{
    private final CountWords parent;

    private int id;

    private Map<String, Integer> words = new HashMap<>();
    private List<String> filesPath;

    MyThread(int id, List<String> filesPath, CountWords parent){
        this.id = id;
        this.filesPath = filesPath;
        this.parent = parent;
    }

    @Override
    public void run() {
        for (String path : filesPath) {
            //System.out.println("Thread " + id + " take new file");
            LineIterator it = null;
            try {
                it = FileUtils.lineIterator(new File(path));
            } catch (Exception e){
                e.printStackTrace();
                return;
            }

            while (it.hasNext()) {
                String str = it.nextLine();
                List<String> newWords = new ArrayList<>(Arrays.asList(str.split("\\W")));
                for(String word : newWords){
                    if("".equals(word))
                        continue;
                    if(words.containsKey(word)){
                        words.replace(word, words.get(word)+1);
                    } else {
                        words.put(word, 1);
                    }
                }
            }
        }
        parent.addWords(words);
    }
}
