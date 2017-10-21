import java.util.ArrayList;

public class Path {
	public ArrayList<Field> fields = new ArrayList<>();
	public int fValue = -1;
	
	public Path(){
		
	}
	
	public Field getLastField(){
		return fields.get(fields.size() - 1);
	}
	
	public Path getNewPath(Field newField) {
		Path newPath = new Path();
		newPath.fields.addAll(this.fields);
		newPath.fields.add(newField);
		return newPath;
	}
	
}
