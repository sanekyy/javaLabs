package lab3;

import java.io.File;
import java.util.*;

/**
 * Created by ihb on 01.12.16.
 */

class CountWords {
    private int THREAD_COUNT = 2;

    private List<String> filesPath = new ArrayList<>();
    private Map<String, Integer> allWords = new TreeMap<>();
    private int countOfRunningThread = 0;

    private String mainPath;

    CountWords(String mainPath) {
        this.mainPath = mainPath;
    }

    void start(){
        fillFileList(new File(mainPath));
        System.out.println("Find " + filesPath.size() + " files");
        startThreads(THREAD_COUNT);
    }

    private void startThreads(int threadCount) {
        int delta = filesPath.size() / THREAD_COUNT;

        List<Thread> treads = new ArrayList<>();
        for(int i=0; i<THREAD_COUNT; i++){
            countOfRunningThread++;
            treads.add(new MyThread(i, filesPath.subList(delta*i, delta*(i+1)), this));
        }

        System.out.println("Start " + countOfRunningThread + " thread");
        long startTime = System.currentTimeMillis();

        for(Thread tread : treads){
                tread.run();
        }

        for(Thread tread : treads){
            try {
                tread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Size " + allWords.size() + " Time " + (System.currentTimeMillis()- startTime));
        System.out.println(allWords);

    }

    private void fillFileList(File dir){
        for(File file :  dir.listFiles()){
            if(file.isDirectory()){
                fillFileList(file);
            } else {
                filesPath.add(file.getAbsolutePath());
            }
        }
    }


    synchronized void addWords(Map<String, Integer> words) {
        Set<Map.Entry<String, Integer>> entrySet = words.entrySet();
        for (Map.Entry<String, Integer> pair : entrySet) {
            if (allWords.containsKey(pair.getKey())) {
                allWords.replace(pair.getKey(), allWords.get(pair.getKey()) + pair.getValue());
            } else {
                allWords.put(pair.getKey(), pair.getValue());
            }
        }
    }
}
