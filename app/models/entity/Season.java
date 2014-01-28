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
	
	public static Finder<Long, Season> find =
			new Finder<Long, Season>(Long.class, Season.class);

	public Option<OptionItem> unique()
	{
		return new OptionItemModelService().findById(id);
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
