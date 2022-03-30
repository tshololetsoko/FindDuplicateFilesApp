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
    ConcurrentHashMap<String, List<String>> map = new ConcurrentHashMap<>();
    LinkedList <List< String >> resultSet = new LinkedList<>();

    public FindDuplicateFiles(File rootFilePath) {
        this.rootFilePath = rootFilePath;
    }

    public LinkedList<List<String>> findDuplicate(File[] paths) throws Exception{
        for (File path : paths) {
            if (path.isDirectory())

                findDuplicate(path.listFiles());
            else {
                String content = getFileContent(path);
                //adding the path to the  content as the
                List<String> list = map.getOrDefault(content, new LinkedList<>());
                list.add(path.getAbsolutePath());
                map.put(content, list);
            }
        }
        //to store the result list which is the absolute path of the file....
        for (String key : map.keySet()) {
            if (map.get(key).size() > 1) {
                resultSet.add(map.get(key));

            }
        }
        return resultSet;
    }

    public String getFileContent(File file) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
        StringBuilder sbf = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            sbf.append(line);
        }
        reader.close();
        return sbf.toString();
    }


}