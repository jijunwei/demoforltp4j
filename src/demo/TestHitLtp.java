package demo;

import java.util.ArrayList;
import java.util.List;

import edu.hit.ir.ltp4j.NER;
import edu.hit.ir.ltp4j.Pair;
import edu.hit.ir.ltp4j.Parser;
import edu.hit.ir.ltp4j.Postagger;
import edu.hit.ir.ltp4j.SRL;
import edu.hit.ir.ltp4j.Segmentor;
import edu.hit.ir.ltp4j.SplitSentence;


public class TestHitLtp {

    public static void main(String[] args) {

    String sent = "国家主席习近平23日就伦敦恐怖袭击事件向英国女王伊丽莎白二世致慰问电，对恐怖袭击事件表示强烈的谴责，对无辜遇难者表示深切的哀悼，向伤者和遇难者家属表示诚挚的慰问。"
        + "一天之内，习近平主席先后同来自大洋洲、亚洲和非洲的贵宾会晤，都谈到了一个共同话题——“一带一路”";

    System.loadLibrary("splitsnt"); // 分句
    System.loadLibrary("segmentor"); // 分词
    System.loadLibrary("postagger");// 词性标注，需要分词
    System.loadLibrary("ner"); // 命名实体识别，需要分词、词性标注
    System.loadLibrary("parser"); // 句法依存分析，需要分词、词性标注
    System.loadLibrary("srl"); // 语义角色标注，需要分词、词性标注、句法依存，Java接口结果不正确
    // jni文件在相关类初始化时已载入，所以不用载入诸如segmentor_jni等dll文件

    String datadir = "D:/ltp4j/ltp_data/";
    // 分句
    ArrayList<String> sents = new ArrayList<String>();
    SplitSentence.splitSentence(sent, sents);
    System.out.println(sents);

    // 分词
    Segmentor.create(datadir + "cws.model");
    List<String> words = new ArrayList<String>();
    int size = Segmentor.segment(sent, words);
    Segmentor.release();
    System.out.println("size=" + size + "\n" + words);

    System.out.println("pos====");
    Postagger.create(datadir + "pos.model");
    List<String> postags = new ArrayList<String>();
    Postagger.postag(words, postags);
    Postagger.release();

    System.out.println("ner====");
    NER.create(datadir + "ner.model");
    List<String> ners = new ArrayList<String>();
    NER.recognize(words, postags, ners);
    NER.release();
    for (int i = 0; i < size; i++) {
        System.out.print(words.get(i) + "/" + postags.get(i) + "<" + ners.get(i) + "> | ");
    }
    System.out.println();

    System.out.println("parser=======");
    Parser.create(datadir + "parser.model");
    List<Integer> heads = new ArrayList<Integer>();
    List<String> deprels = new ArrayList<String>();
    Parser.parse(words, postags, heads, deprels);
    Parser.release();
    for (int i = 0; i < size; i++) {
        System.out.print(heads.get(i) + ":" + deprels.get(i) + "|");
    }
    System.out.println();

    System.out.println("srl====");
    SRL.create(datadir + "srl/");
    List<Pair<Integer, List<Pair<String, Pair<Integer, Integer>>>>> srls = new ArrayList<Pair<Integer, List<Pair<String, Pair<Integer, Integer>>>>>();
    SRL.srl(words, postags, deprels, heads, deprels, srls);
    SRL.release();
    System.out.println(srls.size());
    for (int i = 0; i < srls.size(); ++i) {
        System.out.println(srls.get(i).first + ":");
        for (int j = 0; j < srls.get(i).second.size(); ++j) {
        System.out.println("   tpye = " + srls.get(i).second.get(j).first + " beg = "
            + srls.get(i).second.get(j).second.first + " end = " + srls.get(i).second.get(j).second.second);
        }
    }
  }
}
