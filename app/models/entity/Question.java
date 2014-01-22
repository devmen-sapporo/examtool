package models.entity;

import java.util.*;

/**
 * 問題クラスです。
 * 
 * @author H.Maeda
 * 
 */
public final class Question {

	/**
	 * カテゴリー
	 */
	private Category category;

	/**
	 * 年
	 */
	private int year;

	/**
	 * 期
	 */
	private Season season;

	/**
	 * 問題番号
	 */
	private int no;

	/**
	 * 問題文
	 */
	private String sentence;

	/**
	 * 選択肢
	 */
	private List<Option> options = new ArrayList<Option>();

	/**
	 * コンストラクタです。
	 */
	public Question() {
		super();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param category
	 *            カテゴリー
	 * @param year
	 *            年
	 * @param season
	 *            期
	 * @param no
	 *            問題番号
	 * @param sentence
	 *            問題文
	 * @param options
	 *            選択肢
	 */
	public Question(Category category, int year, Season season, int no,
			String sentence, List<Option> options) {
		super();

		int count = 0;
		for (Option option : options) {
			if (option.isAnswer())
				count++;
		}

		if (!(count == 1))
			throw new IllegalArgumentException();

		this.category = category;
		this.year = year;
		this.season = season;
		this.no = no;
		this.sentence = sentence;
		this.options = options;
	}

	/**
	 * カテゴリを取得します。
	 * 
	 * @return category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * 年を取得します。
	 * 
	 * @return year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * 期を取得します。
	 * 
	 * @return season
	 */
	public Season getSeason() {
		return season;
	}

	/**
	 * 問題番号を取得します。
	 * 
	 * @return no
	 */
	public int getNo() {
		return no;
	}

	/**
	 * 選択肢を取得します。
	 * 
	 * @return options
	 */
	public List<Option> getOptions() {
		return options;
	}

	/**
	 * 問題文を取得します。
	 * 
	 * @return 問題文
	 */
	public String getSentence() {
		return this.sentence;
	}
	
	/**
	 * 試験名を取得します。
	 * 
	 * @return
	 */
	public String getExamName() {
		return String.format("平成%d年 %s %s", this.year, this.season.toString(), this.category.toString());
	}
}
