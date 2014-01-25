package models.entity;

import javax.persistence.*;

import play.db.ebean.*;

/**
 * 選択肢クラスです。
 * @author Hiroyuki
 *
 */
@Entity
public class Option extends Model {
	
	@Id
	public Long id;
	
	/**
	 * センテンス
	 */
	public String sentence;

	/**
	 * 正解かどうかを示す値
	 */
	public boolean isAnswer;

	public Option() {}
	
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
	
	public static Finder<Long, Option> find =
			new Finder<Long, Option>(Long.class, Option.class);

	
}
