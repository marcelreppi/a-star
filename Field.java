
public class Field {
	public int column;
	public int row;
	public int fValue;
	
	public FieldType type;
	
	public Field(int row, int column, FieldType type){
		this.column = column;
		this.row = row;
		this.type = type;
	}
	
	@Override
	public String toString() {
		return type.toString();
	}
}
