import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

public class FindDuplicateFileNonThreadSolution {
    private final static Logger log = Logger.getLogger("FindDuplicateFileNonThreadSolution");
    DuplicateFilesFinder2 handler;
    File[] root;

    @Before
    public void setup() throws Exception {
        //to find the system's root path
        root = File.listRoots();
    }

    @Test
    public void testDuplicateFilesFinder() throws Exception {

        for (File file : root) {
            File[] directories = file.listFiles(File::isDirectory);
          //  List<File> files =  new ArrayList<>() ;
            File [] files = (directories[0].getAbsoluteFile().listFiles(File::isFile));

            String [] str= {"root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)","root 4.txt(efgh)"};
            System.out.print(directories[0].getAbsoluteFile());

            DuplicateFilesFinder2 duplicateFilesFinder = new DuplicateFilesFinder2(root);
           resultSet = duplicateFilesFinder.findDuplicate(paths);


            }
        }



}
