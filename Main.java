
public class Main {
	public static void main(String[] args) {
		//8x7
		String a = 	"0000000" +
					"0111100" +
					"0000100" +
					"01M01P0" +
					"0010100" +
					"0000100" +
					"0000000" +
					"0000000";
		
		//10x10
		String b = 	"M010000000" +
					"0110001000" +
					"0111101000" +
					"001P101110" +
					"0010000110" +
					"1001111000" +
					"0000011101" +
					"0000010000" +
					"0000000000" +
					"1111111111";
		

		
		String c = 	"000M100000000000000" +
					"00001000011100P0000" +
					"1101110001110000000" +
					"1101111001111111100" +
					"1000100000000000000" +
					"0010100000000000000" +
					"0000100011111110000" +
					"0000000000000000000";
		
//		Map map = new Map(a, 8, 7);		
//		Map map = new Map(b, 10, 10);
		Map map = new Map(c, 8, 19);
		
		map.printBoard();
		
		AStarBot bot = new AStarBot(map);
		
		if(bot.findThePrincess()){
			for (int i = 1; i < bot.finalPath.fields.size() - 1; i++) {
				Field f = bot.finalPath.fields.get(i);
				map.board[f.row][f.column].type = FieldType.PATH;
			}
			
			map.printBoard();
			
		}else{
			System.out.println("There is no way to the princess...");
		}
		
		
	}
}
