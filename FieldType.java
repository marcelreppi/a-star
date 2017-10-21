
public enum FieldType {
	EMPTY('_'), OBSTACLE('#'), MARIO('M'), PRINCESS('P'), PATH('X');
	
	private char sign;
	private FieldType(char sign) {
		this.sign = sign;
	}
	
	@Override
	public String toString() {
		return String.valueOf(sign);
	}
}
