package models.entity;

/**
 * 解答クラスです。
 * 
 * @author Hiroyuki
 * 
 */
public class AnswerColumn {
	/**
	 * @param question
	 */
	public AnswerColumn(Question question) {
		super();
		this.question = question;
	}

	/**
	 * 問題を取得する。
	 * @return question
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * 選んだ選択肢を取得します。
	 * @return selectedOptionItem
	 */
	public OptionItem getSelectedOption() {
		return selectedOptionItem;
	}

	/** 問題 */
	private Question question;

	/** 選んだ選択肢 */
	private OptionItem selectedOptionItem;

	/**
	 * 解答します。
	 * 
	 * @param selectedOptionItem
	 *            選んだ選択肢
	 */
	public void markAnswer(OptionItem selectedOptionItem) {
		this.selectedOptionItem = selectedOptionItem;
	}

	/**
	 * 正解かどうかを返します。
	 * 
	 * @return 正解の場合 true を返します。
	 */
	public boolean isAnswer() {
		if (this.selectedOptionItem == null)
			return false;
		return this.selectedOptionItem.isAnswer;
	}

}
