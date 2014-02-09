package models.entity;

public class ExamCategory {
	/**
	 * カテゴリー
	 */
	public Category category;
	/**
	 * 年
	 */
	public int year;
	/**
	 * 期
	 */
	public Season season;

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
	 * 試験名を取得します。
	 * 
	 * @return
	 */
	public String getExamName() {
		return String.format("平成%d年 %s %s", this.year, this.season.name, this.category.name);
	}

}