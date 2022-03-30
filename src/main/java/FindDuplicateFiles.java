import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author TSHOLO LETSOKO
 * March 2022
 */

public class FindDuplicateFiles {

    File rootFilePath;

    HashMap<String, List<String>> map = new HashMap<>();
    LinkedList <List< String >> resultSet = new LinkedList<>();

    public FindDuplicateFiles() {
    }

    public LinkedList<List<String>> findDuplicate(File[] paths) throws Exception{
        for (File path : paths) {
            if (path.isDirectory()) { //if the current file is a subfolder - do recursive
                findDuplicate(path.listFiles());
            } else {
                String content = readFileContent(path);
                //content is used as the key in the map, value stored is the absolute path
                List<String> list = map.getOrDefault(content, new LinkedList<>());
                list.add(path.getAbsolutePath());
                map.put(content, list);
                // list is the reference object - if there are two list items, then we can update the duplicate list as well
                if (list.size() ==2) {
                    resultSet.add(list);
                }
            }
        }
        return resultSet; //returning absolute paths where a duplicate file was found
    }



    //read content of the file - used for comparison
    public String readFileContent(File file) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
        StringBuilder sbf = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            sbf.append(line);
        }
        reader.close(); //need to close buffer
        return sbf.toString();
    }

}