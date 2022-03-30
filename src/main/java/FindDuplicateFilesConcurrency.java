import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author TSHOLO LETSOKO
 * March 2022
 */

public class FindDuplicateFilesConcurrency implements  Runnable{

    File rootFilePath;
    static ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> map = new ConcurrentHashMap<>();
    static ConcurrentLinkedQueue <ConcurrentLinkedQueue< String >> resultSet = new ConcurrentLinkedQueue<>(); //result set from thread

    public FindDuplicateFilesConcurrency(File rootFilePath) {
        this.rootFilePath = rootFilePath;
    }

    static public  ConcurrentLinkedQueue <ConcurrentLinkedQueue< String >> searchFileSystem() {
        LinkedList <Thread>  list=new LinkedList<>();
        for ( File file : File.listRoots()) { //search the entire system
            list.add(createSearchThread(file));
        }
        for (Thread rootThread: list
             ) {
            try {
                rootThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return resultSet;
    }

    public static Thread createSearchThread(File file){
        Thread thread = new Thread(new FindDuplicateFilesConcurrency(file));
        thread.start();
        return thread;
    }

    public void findDuplicate(){

        File [] paths = rootFilePath.listFiles();
        for (File path : paths) {

            if (path.canWrite()){
                //if the current file is a subfolder - do recursive
                createSearchThread(path);  //create a thread when path is a directory
            } else {
                String content = readFileContent(path);
                //content is used as the key in the map, value stored is the absolute path
                ConcurrentLinkedQueue<String> list = map.getOrDefault(content, new ConcurrentLinkedQueue<>());
                list.add(path.getAbsolutePath());
                map.put(content, list);
                // list is the reference object - if there are two list items, then we can update the duplicate list as well
                if (list.size() == 2) {
                    resultSet.add(list);
                }
            }
        }
    }

    //read content of the file - used for comparison
    public String readFileContent(File file) {
        BufferedReader reader = null;
        StringBuilder sbf = new StringBuilder();

        try {
            reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
            String line;

            while ((line = reader.readLine()) != null) {
                sbf.append(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sbf.toString(); // close buffer
    }

    @Override
    public void run() {
        findDuplicate();
    }

    public static void main(String [] arg) {
        searchFileSystem();
        //print out the duplicates found in system
        for (ConcurrentLinkedQueue<String> resultSt : resultSet) {
            for (String finalList : resultSt) {
                System.out.println(finalList);
            }
        }
    }
}