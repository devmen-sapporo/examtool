package models.entity;

import java.util.*;

/**
 * 解答用紙クラスです。
 * 
 * @author A.Suzuki
 * 
 */
public final class AnswerSheet {

	/** 実施日 */
	private final Calendar operationDate;

	/** ユーザ */
	private User user;

	/** 解答用紙 */
	private QuestionSheet questionSheet;

	/** 解答のリスト **/
	private ArrayList<AnswerColumn> answerColumns = new ArrayList<>();

	/** 終了済かどうかを示すリスト */
	private boolean isFinished = false;

	/** 点数 */
	private int score = 0;

	private int currentIndex = 0;
	/**
	 * コンストラクタ
	 * 
	 * @param user
	 *            ユーザ
	 * @param questionSheet
	 *            解答用紙
	 */
	public AnswerSheet(User user, QuestionSheet questionSheet) {
		this.user = user;
		this.questionSheet = questionSheet;
		this.operationDate = Calendar.getInstance();
		this.makeAnswerColumns();
	}

	private void makeAnswerColumns() {
		for(Question question: this.questionSheet.getQuestions()) {
			AnswerColumn answerColumn = new AnswerColumn(question);
			this.answerColumns.add(answerColumn);
		}
	}

	/**
	 * 実施日を取得します。
	 * 
	 * @return 実施日
	 */
	public Calendar getOperationDate() {
		return operationDate;
	}

	/**
	 * 採点します。
	 */
	public void mark() {
		this.isFinished = true; 
		
		this.score = 0;
		for(AnswerColumn answerColumn : this.answerColumns)
		{
			if (answerColumn.isAnswer()) this.score++;
		}
	}

	/**
	 * 問題数を取得します。
	 * 
	 * @return 問題数
	 */
	public int getQuestionCount() {
		return this.questionSheet.getQuestions().size();
	}

	/**
	 * @return user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * 問題シートを取得します。
	 * @return questionSheet
	 */
	public QuestionSheet getQuestionSheet() {
		return questionSheet;
	}

	/**
	 * 得点欄を取得します。
	 * @return answerColumns
	 */
	public ArrayList<AnswerColumn> getAnswerColumns() {
		return answerColumns;
	}

	/**
	 * 解答が終了したかどうかを取得します。
	 * @return isFinished 終了している場合 true を返します。
	 */
	public boolean isFinished() {
		return isFinished;
	}

	/**
	 * 得点を取得します。
	 * @return score 得点
	 */
	public int getScore() {
		if (!isFinished) throw new IllegalStateException("isFinish is false");
		return score;
	}

	public AnswerColumn getCurrentAnswerColumn() {
		return this.answerColumns.get(this.currentIndex);
	}
}
