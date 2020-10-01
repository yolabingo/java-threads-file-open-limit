import java.io.*;
import java.util.*;

class FileOpener implements Runnable {
      private String filename;

      public FileOpener() {
      }

      public FileOpener(String filename) throws IOException {
          try {
              new FileReader(filename);
	  } catch (IOException e) {
    	      e.printStackTrace();
	  }	  
      }

      public void run() {
      }
}


public class OpenFiles {
    public static void main(String[] args) throws IOException {
	int numThreads = Integer.parseInt(args[0]);
        List<Thread> aThreads = new ArrayList<Thread>();
	int fileCount = 1;
        try {
	    while (true) {
		  String filename = String.format("/tmp/javathreads-%s", fileCount);
		  File file = new File(filename);
		  file.createNewFile();
		  for (int n = 0; n < numThreads; n = n + 1) {
                      FileOpener f = new FileOpener(filename);
		      Thread t = new Thread(f);
		      t.start();
		      aThreads.add(t);
		  }
	          fileCount++;
	    }
	} catch (IOException e) {
		System.out.println("# of threads: " + numThreads);
		System.out.println("# of files: " + fileCount);
	        e.printStackTrace();
        }
    }
}
