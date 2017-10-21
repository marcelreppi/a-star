import java.util.ArrayList;
import java.util.Comparator;

public class AStarBot {
	
	public final int actionCost = 1;
	public Map map;
	
	//contains all paths that have not been explored yet
	public ArrayList<Path> sortedPathOptions = new ArrayList<>();
	
	//contains all paths that have been explored
	public ArrayList<Path> exploredPaths = new ArrayList<>();
	
	//list of fields that were explored and have a calculated f value
	public ArrayList<Field> exploredFields = new ArrayList<>();
	
	public Path finalPath = null;

	
	public AStarBot(Map map) {
		this.map = map;

		//set starting path
		Path startingPath = new Path();
		startingPath.fields.add(map.marioField);
		startingPath.fValue = calcFValue(startingPath);
		
		sortedPathOptions.add(startingPath);
		
		//field always stores current minimal f value
		map.marioField.fValue = startingPath.fValue;
		exploredFields.add(map.marioField);
	}
	
	public boolean findThePrincess(){
		while(true){
			if(sortedPathOptions.size() == 0){
				return false;
			}
			Path currentPath = sortedPathOptions.get(0);
			sortedPathOptions.remove(0);
			exploredPaths.add(currentPath);
			
			
			Field currentField = currentPath.getLastField();
			
			if(currentField == map.princessField){
				finalPath = currentPath;
				return true;
			}
			
			Field[] surroundings = map.getSurroundingFields(currentField);
			
			for(Field neighbor : surroundings){
				if(neighbor == null || neighbor.type == FieldType.OBSTACLE){
					continue;
				}
				//prevent circles
				if(currentPath.fields.contains(neighbor)){
					continue;
				}
				
				Path newPath = currentPath.getNewPath(neighbor);
				newPath.fValue = calcFValue(newPath);
				
				//dynamic programming
				if(exploredFields.contains(neighbor)){
					//remember that fields always store current (previous) minimal f value
					if(newPath.fValue >= neighbor.fValue){
						continue;
					}else{
						neighbor.fValue = newPath.fValue;
						sortedPathOptions.add(newPath);
					}
				}else{
					exploredFields.add(neighbor);
					neighbor.fValue = newPath.fValue;
					sortedPathOptions.add(newPath);
				}
			}
			
			sortedPathOptions.sort(new Comparator<Path>() {
				@Override
				public int compare(Path p1, Path p2) {
					int diff = p1.fValue - p2.fValue;
					if(diff == 0){
						return 0;
					}else if(diff < 0){
						return -1;
					}else{
						return 1;
					}
				}
			});
		}
	}
	
	public int calcFValue(Path path){
		return (path.fields.size() - 1) * actionCost + map.getHeuristicValue(path.getLastField());
	}	
}
