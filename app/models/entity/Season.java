package models.entity;

import javax.persistence.*;

import models.service.Examination.*;
import play.db.ebean.*;
import play.libs.F.Option;

/**
 * @author Hiroyuki
 *
 */
@Entity
public class Season extends Model{
	
	@Id
	public Long id;

	/** シーズン名称 */
	public String name;
	
	/** 省略名称 */
	public String attrName;
	
	public static Finder<Long, Season> find =
			new Finder<Long, Season>(Long.class, Season.class);
	
	/**
	 * コンストラクタ
	 * @param id
	 */
	public Season(long id) {
		this.id = id;
	}

	public Option<Season> unique()
	{
		return new SeasonModelService().findById(id);
	}
	
//	/** なし */
//	None,
//	/** 春期 */
//	Spring,
//	/** 秋期 */
//	Autumn;
//
//	@Override
//	public String toString() {
//	    switch(this) {
//	      case None: return "なし";
//	      case Spring: return "春期";
//	      case Autumn: return "秋期";
//	      default: throw new IllegalArgumentException();
//	    }
//	}
}
