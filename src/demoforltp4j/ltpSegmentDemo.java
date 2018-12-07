package demoforltp4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.hit.ir.ltp4j.Segmentor;

public class ltpSegmentDemo {
    public static void main(String[] args) throws IOException {
        if (Segmentor.create("D:\\ltp4j\\ltp_data\\cws.model") < 0) {
              System.err.println("load failed");
              return;
          }
        
          String sent = FileUtil.readFileAsString("d:/ubuntuDir/input.txt");
          //String sent = FileUtil.readFileAsString("d:/111/input.txt");
          List<String> words = new ArrayList<String>();
          int size = Segmentor.segment(sent, words);
          for (int i = 0; i < size; i++) {
              System.out.print(words.get(i));
              if (i == size - 1) {
                  System.out.println();
              } else {
                  System.out.print("\t");
              }
          }
          Segmentor.release();
    }
}
