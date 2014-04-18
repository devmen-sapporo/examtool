package models.entity;

import javax.persistence.*;

import models.service.Examination.*;
import play.db.ebean.*;
import play.libs.F.Option;

/**
 * 試験区分
 * @author H.Maeda
 */
@Entity
public class Category extends Model{
	
	public Category(long id) {
		this.id = id;
	}

	@Id
	public Long id;

	/** カテゴリ名称 */
	public String name;
	
	/** 省略名称 */
	public String attrName;
	
	public static Finder<Long, Category> find =
			new Finder<Long, Category>(Long.class, Category.class);

	public Option<Category> unique()
	{
		return new CategoryModelService().findById(id);
	}
	
//	@Override
//	public String toString() {
//	    switch(this) {
//	      case FE: return "基本情報技術者";
//	      case AP: return "応用情報技術者";
//	      default: throw new IllegalArgumentException();
//	    }
//	}
}
