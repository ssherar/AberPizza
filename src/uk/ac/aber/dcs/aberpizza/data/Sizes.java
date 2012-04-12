package uk.ac.aber.dcs.aberpizza.data;

public enum Sizes {
	SMALL,
	MEDIUM,
	LARGE,
	CAN,
	SMALL_BOTTLE,
	LARGE_BOTTLE;
	
	@Override
	public String toString() {
		String[] s = super.toString().split("_");
		String ret = "";
		for(int i = 0; i < s.length; i++) {
			s[i] = s[i].substring(0, 1) + s[i].substring(1, s[i].length()).toLowerCase();
			ret = s[i] + " ";
		}
		ret = ret.substring(0, ret.length() - 1);
		return ret;
	}
	
}
