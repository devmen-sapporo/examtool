package models.entity;

import java.util.*;

import javax.persistence.*;

import models.service.Examination.*;
import play.db.ebean.*;
import play.libs.F.Option;

/**
 * 解答用紙クラスです。
 * 
 * @author A.Suzuki
 * 
 */
@Entity
public final class AnswerSheet extends Model {
	
	protected class AnswerColumnComparator implements Comparator<AnswerColumn>  {

		@Override
		public int compare(AnswerColumn o1, AnswerColumn o2) {
	        int no1 = o1.question.no;
	        int no2 = o2.question.no;

	        if (no1 > no2) {
	            return 1;
	        } else if (no1 == no2) {
	            return 0;
	        } else {
	            return -1;
			}
		}
	}
	
	@Id
	public Long id;

	/** 実施日時 */
	public Calendar operationDate = null;

	/** ユーザ */
	@ManyToOne(cascade=CascadeType.ALL)
	public Account account;

	/** 解答欄のリスト **/
	@OneToMany(cascade=CascadeType.ALL)
	public List<AnswerColumn> answerColumns = new ArrayList<>();

	/** 終了済かどうかを示すリスト */
	public boolean isFinished = false;

	/** 点数 */
	private int score = 0;
	
	private int currentIndex = 0;

	public AnswerSheet(long id) {
		this.id = id;
	}

	/**
	 * コンストラクタ
	 * 
	 * @param account
	 *            ユーザ
	 * @param questionSheet
	 *            解答用紙
	 */
	public AnswerSheet(Account account, QuestionSheet questionSheet) {
		this.account = account;
		this.makeAnswerColumns(account, questionSheet);
		this.operationDate = Calendar.getInstance();
	}

	private void makeAnswerColumns(Account account, QuestionSheet questionSheet) {
		for(Question question: questionSheet.questions) {
			AnswerColumn answerColumn = new AnswerColumn(account, question);
			this.answerColumns.add(answerColumn);
		}
	}

	/**
	 * 採点します。
	 */
	public void mark() {
		this.isFinished = true; 
		
		this.score = 0;
		for(AnswerColumn answerColumn : this.answerColumns)
		{
			if (answerColumn.isCorrect()) this.score++;
		}
	}

	/**
	 * 問題数を取得します。
	 * 
	 * @return 問題数
	 */
	public int getQuestionCount() {
		return this.answerColumns.size();
	}
	
	/**
	 * 回答済みの問題数を取得します。
	 * 
	 * @return 回答済みの問題数
	 */
	public int getAnsweredCount() {
		int count = 0;
		for(AnswerColumn answerColumn : this.answerColumns)
		{
			if (answerColumn.isAnswered()) count++;
		}
		
		return count;
	}
	
	/**
	 * 未回答の問題数を取得します。
	 * 
	 * @return 未回答の問題数
	 */
	public int getUnansweredCount() {
		return this.answerColumns.size() - getAnsweredCount();
	}
	

	/**
	 * 得点欄を取得します。
	 * @return answerColumns
	 */
	public List<AnswerColumn> getAnswerColumns() {
		Collections.sort(answerColumns, new AnswerColumnComparator());
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
	
	public static Finder<Long, AnswerSheet> find =
			new Finder<Long, AnswerSheet>(Long.class, AnswerSheet.class);

	public Option<AnswerSheet> unique()
	{
		return new AnswerSheetModelService().findById(id);
	}
}
