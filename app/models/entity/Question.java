package models.entity;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;

/**
 * 問題クラスです。
 * 
 * @author H.Maeda
 *
 */
@Entity
public class Question extends Model{

	
	@Id
	public Long id;
	
	/**
	 * カテゴリー
	 */
	@ManyToOne
	public Category category;

	/**
	 * 年
	 */
	public int year;

	/**
	 * 期
	 */
	@ManyToOne
	public Season season;

	/**
	 * 問題番号
	 */
	public int no;

	/**
	 * 問題文
	 */
	public String sentence;

	/**
	 * 選択肢
	 */
	@OneToMany(cascade = CascadeType.ALL)
	public List<Option> options = new ArrayList<Option>();

	public static Finder<Long, Question> find =
			new Finder<Long, Question>(Long.class, Question.class);

//	/**
//	 * コンストラクタ
//	 */
//	public Question(){}
//	
	
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
			if (option.isAnswer)
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
	 * 試験名を取得します。
	 * 
	 * @return
	 */
	public String getExamName() {
		return String.format("平成%d年 %s %s", this.year, this.season.toString(), this.category.toString());
	}
	
	@Override
	public String toString() {
		return ("[id:" + id + ", ExamName:" + "" + ", sentence:" + sentence + "]");
	}

}
