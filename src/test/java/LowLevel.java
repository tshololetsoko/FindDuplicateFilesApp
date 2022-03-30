import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

public class LowLevel {
        /**
         * @param args
         */
        public static void main(String[] args) {
            File dir = new File(".");
            File[] fileList = dir.listFiles();
            for (int x = 0; x < fileList.length; x++) {
                for (int y = x+1; y < fileList.length; y++) {
                    if (fileList[x].length() == fileList[y].length()) {
                        if (CompareFiles(fileList[x], fileList[y])) {
                            System.out.println(fileList[y]);
                        }
                    }
                }
            }
        }

        public static boolean CompareFiles(File x, File y) {
            try {
                FileInputStream xs = new FileInputStream(x);
                FileInputStream ys = new FileInputStream(y);
                System.out.println("Compare: " + x + " vs " + y);
                boolean result = true;
                while (result == true) {
                    int xb = xs.read();
                    int yb = ys.read();
                    if (xs.read() != ys.read()) {
                        System.out.println("Compare: " + x + " vs " + y + " =false");
                        result = false;
                        break;
                    }
                    if (xb == -1)
                        break;
                }
                return result;
            } catch (FileNotFoundException e) {
                return false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return false;
        }

    }
