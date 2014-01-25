package models.entity;

import javax.persistence.*;

import play.db.ebean.*;

/**
 * 試験区分
 * @author H.Maeda
 */
@Entity
public class Category extends Model{
	
	@Id
	public Long id;

	/** カテゴリ名称 */
	public String name;
	
	public static Finder<Long, Category> find =
			new Finder<Long, Category>(Long.class, Category.class);


	
//	/** 基本情報技術者 */
//	FE,
//	/** 応用 情報技術者 */
//	AP;
//
//	@Override
//	public String toString() {
//	    switch(this) {
//	      case FE: return "基本情報技術者";
//	      case AP: return "応用情報技術者";
//	      default: throw new IllegalArgumentException();
//	    }
//	}
}
