package models.entity;

public class ExamCategory {
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
	 * コンストラクタ
	 * @param category カテゴリ
	 * @param year 年
	 * @param season 期
	 */
	public ExamCategory(Category category, int year, Season season) {
		this.category = category;
		this.year = year;
		this.season = season;
	}

	/**
	 * カテゴリを取得します。
	 * 
	 * @return category
	 */
	public Category getCategory() {
		return this.category;
	}

	/**
	 * 年を取得します。
	 * 
	 * @return year
	 */
	public int getYear() {
		return this.year;
	}

	/**
	 * 期を取得します。
	 * 
	 * @return season
	 */
	public Season getSeason() {
		return this.season;
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