package uk.ac.aber.dcs.aberpizza.data;

// TODO: Auto-generated Javadoc
/**
 * The Enum Sizes.
 */
public enum Sizes {
	
	/** The SMALL size for pizzas. */
	SMALL,
	
	/** The MEDIUM size for pizzas. */
	MEDIUM,
	
	/** The LARGE size for pizzas. */
	LARGE,
	
	/** The CAN size for drinks. */
	CAN,
	
	/** The SMALL bottle size for drinks. */
	SMALL_BOTTLE,
	
	/** The LARGE bottle size for drinks. */
	LARGE_BOTTLE;
	
	/**
	 * This overriden method changes the enums textual value to be properly capitalised
	 * @returns the formatted String
	 */
	@Override
	public String toString() {
		String[] s = super.toString().split("_");
		String ret = "";
		for(int i = 0; i < s.length; i++) {
			ret += s[i].substring(0, 1) + s[i].substring(1, s[i].length()).toLowerCase() + " ";
		}
		ret = ret.substring(0, ret.length() - 1);
		return ret;
	}
	
}
