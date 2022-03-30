import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

public class FindDuplicateFileTest {
    private final static Logger log = Logger.getLogger("FindDuplicateFileNonThreadSolution");
    File root;
    @Before
    public void setup() {
        root = new File("C:\\Users\\OOL\\FindDuplicateFilesApp\\src\\test\\TestFiles\\");
    }

    @Test
    public void testDuplicateFilesFinder() throws Exception {

        LinkedList<List<String>> findDuplicate =new FindDuplicateFiles().findDuplicate(root.listFiles());

      // System.out.println(findDuplicate);
      for (List<String> currentBatch: findDuplicate){
            if (currentBatch.contains(root.getAbsolutePath() + "\\test4.txt"))
                assertTrue(currentBatch.contains((root.getAbsolutePath() + "\\TestFiles2\\test2.txt")) &&
                        currentBatch.contains((root.getAbsolutePath() + "\\TestFiles2\\test6.txt")) && currentBatch.size()==3);
          if (currentBatch.contains(root.getAbsolutePath() + "\\test5.txt"))
              assertTrue(currentBatch.contains((root.getAbsolutePath() + "\\TestFiles2\\test3.txt")) && currentBatch.size()==2);
          if (currentBatch.contains(root.getAbsolutePath() + "\\TestFiles2\\test1.txt"))
              assertTrue(currentBatch.size() == 1);

      }


        }


}
