
public class Map {
	
	public Field[][] board;
	public String boardString;
	public int rows;
	public int columns;
	public Field marioField;
	public Field princessField;
	
	public Map(String s, int rows, int columns){
		this.boardString = s;
		this.rows = rows;
		this.columns = columns;
		this.board = new Field[rows][columns];
		
		//fill board from string
		for(int i = 0; i < rows; i++){
		    for(int j = 0; j < columns; j++){
		    	char sign = boardString.charAt(i*columns + j);
		    	if(sign == '0'){
		    		board[i][j] = new Field(i,j, FieldType.EMPTY);
		    	}else if(sign == '1'){
		    		board[i][j] = new Field(i,j, FieldType.OBSTACLE);
		    	}else if(sign == 'M'){
		    		board[i][j] = new Field(i,j, FieldType.MARIO);
		    		marioField = board[i][j];
		    	}else if(sign == 'P'){
		    		board[i][j] = new Field(i,j, FieldType.PRINCESS);
		    		princessField = board[i][j];
		    	}
		    }
		}
	}
	
	public void printBoard(){
		String output = "";
		for(int i = 0; i < rows; i++){
		    for(int j = 0; j < columns; j++){
		    	output += board[i][j];
		    	if(j == columns - 1){
		    		output += "\n";
		    	}
		    }
		}
		System.out.println(output);
	}
	
	public Field getField(int row, int column){
		if(row < 0 || row > this.rows - 1 || column < 0 || column > this.columns - 1){
			return null;
		}
		return board[row][column];
	}
	
	public Field[] getSurroundingFields(Field field){
		Field[] list = new Field[4];
		
		Field f1 = getField(field.row - 1, field.column);
		Field f2 = getField(field.row + 1, field.column);
		Field f3 = getField(field.row, field.column - 1);
		Field f4 = getField(field.row, field.column + 1);
		
		list[0] = f1;
		list[1] = f2;
		list[2] = f3;
		list[3] = f4;
		
		return list;
	}
	
	public int getHeuristicValue(Field field){
		return Math.abs((princessField.column - field.column)) + Math.abs((princessField.row - field.row));
	}	
}
