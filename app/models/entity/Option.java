package models.entity;

/**
 * 選択肢クラスです。
 * @author Hiroyuki
 *
 */
public class Option {
	/**
	 * センテンス
	 */
	private String sentence;

	/**
	 * 正解かどうかを示す値
	 */
	private boolean isAnswer;

	/**
	 * コンストラクタ
	 * @param sentence センテンス
	 * @param isAnswer 正解かどうかを示す値
	 */
	public Option(String sentence, boolean isAnswer) {
		super();
		this.sentence = sentence;
		this.isAnswer = isAnswer;
	}
	
	/**
	 * 選択肢文を取得します。
	 * @return 選択肢文
	 */
	public String getSentence() {
		return sentence;
	}

	/**
	 * 正解かどうかを示す値を取得します。
	 * @return
	 */
	public boolean isAnswer() {
		return isAnswer;
	}
	
	
}
