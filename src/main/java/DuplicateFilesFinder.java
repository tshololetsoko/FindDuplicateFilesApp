
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class DuplicateFilesFinder {
    private final static Logger log = Logger.getLogger(String.valueOf(DuplicateFilesFinder2.class));
    File [] rootFilePath;

        public DuplicateFilesFinder( File [] rootFilePath){
            this.rootFilePath = rootFilePath;
        }

        public HashMap< String, List< String >> findDuplicate(String[] paths) {
            HashMap< String, List < String >> map = new HashMap < > ();

            for (String path: paths) {
                String[] values = path.split(" ");
                for (int i = 1; i < values.length; i++) {
                    String[] name_cont = values[i].split("\\(");
                    name_cont[1] = name_cont[1].replace(")", "");
                    List<String> list = map.getOrDefault(name_cont[1], new ArrayList<String>());
                    list.add(values[0] + "/" + name_cont[0]);
                    map.put(name_cont[1], list);
                }
            }

            HashMap< String, List < String >> res = new   HashMap< String, List < String >> ();
            for (String key: map.keySet()) {
                // if (map.get(key).size() > 1)
                //res.(map.get(key))
            }
            return res;
        }

}



