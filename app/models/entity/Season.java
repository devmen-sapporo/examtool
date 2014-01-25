package models.entity;

import javax.persistence.*;

import play.db.ebean.*;

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
