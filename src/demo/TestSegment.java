package demo;

import java.util.ArrayList;
import java.util.List;

import edu.hit.ir.ltp4j.Segmentor;

/**
 * 
 * <p>
 * ClassName TestSegment
 * </p>
 * <p>
 * Description 分词接口
 * </p>
 * 
 * @author TKPad wangx89@126.com
 *         <p>
 *         Date 2015年5月7日 下午10:09:23
 *         </p>
 * @version V1.0.0
 *
 */
public class TestSegment {
	public static void main(String[] args) {
		// /MyTest/
		if (Segmentor.create("d:/ltp4j/ltp_data/cws.model") < 0) {
			System.err.println("load failed");
			return;
		}
		String sent = "我是中国人";
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
