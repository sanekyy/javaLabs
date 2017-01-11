import java.io.File;
import java.io.IOException;

/**
 * Created by ihb on 25.11.16.
 */
public class WarAndWorld {

    static int charactersCount = 0;
    static int wordsCount = 0;
    static int fileCount = 0;

    public static void main(String[] args) throws IOException {

        /*LineIterator it = FileUtils.lineIterator(new File("src/main/resources/WWW.txt"));

        while (it.hasNext()){
            String str = it.nextLine();
            charactersCount += str.length();
            wordsCount += str.split(" ").length;
        }

        System.out.println(charactersCount);
        System.out.println(wordsCount);


        /*long threadCount = FileUtils.sizeOf(new File("src/main/resources/WWW.txt"))/50000;
        for(int i=0; i<threadCount; i++){
            new Thread().
        }
        reader.skip(2000000);*/




        //it.close();

        /*Scanner scanner = new Scanner(new File("src/main/resources/matrixA"));

        charactersCount = 0;
        wordsCount = 0;

        while (scanner.hasNextLine()){
            String str = scanner.nextLine();
            charactersCount += str.length();
            wordsCount += str.split(" ").length;
        }

        System.out.println(charactersCount);
        System.out.println(wordsCount);*/


        printDirectory(new File("/home/ihb/Documents/Projects"),0);
        System.out.println(fileCount);
    }

    static void printDirectory(File dir, int level){
        for(File file :  dir.listFiles()){
            fileCount++;
            if(file.isDirectory()){
                System.out.println(file.getAbsolutePath());
                printDirectory(file, level+1);
            } else {
                System.out.println(file.getAbsolutePath());
            }
        }
    }
}
