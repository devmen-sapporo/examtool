package models.entity;

/**
 * 試験区分
 * @author H.Maeda
 */
public enum Category {
	/** 基本情報技術者 */
	FE,
	/** 応用 情報技術者 */
	AP;

	@Override
	public String toString() {
	    switch(this) {
	      case FE: return "基本情報技術者";
	      case AP: return "応用情報技術者";
	      default: throw new IllegalArgumentException();
	    }
	}
}
