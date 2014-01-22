package models.entity;

/**
 * @author Hiroyuki
 *
 */
public enum Season {
	/** なし */
	None,
	/** 春期 */
	Spring,
	/** 秋期 */
	Autumn;

	@Override
	public String toString() {
	    switch(this) {
	      case None: return "なし";
	      case Spring: return "春期";
	      case Autumn: return "秋期";
	      default: throw new IllegalArgumentException();
	    }
	}
}
