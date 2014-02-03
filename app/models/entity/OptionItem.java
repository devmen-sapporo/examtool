package models.entity;

import javax.persistence.*;

import models.service.Examination.*;
import play.db.ebean.*;
import play.libs.F.Option;



/**
 * 選択肢クラスです。
 * @author Hiroyuki
 *
 */
@Entity
public class OptionItem extends Model {
	@Transient
	private OptionItemModelService checkService = new OptionItemModelService();

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

	@ManyToOne
	public Question question;
	
    @Transient
    private OptionItemService optionItemService = new OptionItemService();
    @Transient
    private OptionItemModelService optionItemModelService = new OptionItemModelService();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
	public OptionItem() {}
	
	public OptionItem(Long id) 
	{
		this.id = id;
	}
	
	/**
	 * コンストラクタ
	 * @param sentence センテンス
	 * @param isAnswer 正解かどうかを示す値
	 */
	public OptionItem(String sentence, boolean isAnswer) {
		super();
		this.sentence = sentence;
		this.isAnswer = isAnswer;
	}
	
	public static Finder<Long, OptionItem> finder =
			new Finder<Long, OptionItem>(Long.class, OptionItem.class);

	public Option<OptionItem> unique()
	{
		return new OptionItemModelService().findById(id);
	}
}
