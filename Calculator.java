import java.util.Scanner;

public class Calculator{
	
	public static double Calculate(String input){
		input = formatEquation(input);
		Stack vals = new Stack<Double>();
		Stack ops = new Stack<String>();
		Scanner sc = new Scanner(input);
		
		while(sc.hasNext()){
			
			String s = sc.next();
			
			if(s.equals("(")){
			}
			else if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^")){
				ops.push(s);
			}
			else if(s.equals(")")){
				String op = ops.pop().toString();
				double v2 = Double.parseDouble(vals.pop().toString());
				double v1 = Double.parseDouble(vals.pop().toString());
				
				if(op.equals("+"))
					vals.push(v1 + v2);
				else if (op.equals("-"))
					vals.push(v1 - v2);
				else if (op.equals("*"))
					vals.push(v1 * v2);
				else if (op.equals("/"))
					vals.push(v1 / v2);
				else if (op.equals("^"))
					vals.push(Math.pow(v1, v2));
			}
			else{
				vals.push(s);
			}
		}
		return Double.parseDouble(vals.pop().toString());
	}
	
	private static String formatEquation(String input){
		String result = "";
		
		for(int i = 0; i < input.length(); i++){
			
			char curr = input.charAt(i);
			int ascii = (int)curr;
			
			int temp = i;
			if(ascii < 58 && ascii > 47){
				while(ascii < 58 && ascii > 47){
					result += curr;
					temp += 1;
					curr = input.charAt(temp);
					ascii = (int)curr;
				}
				i = temp - 1;
				result += " ";
			}
			
			else if(curr != ' ')
				result += curr + " ";
		}
		
		return result;
	}
	
	public static void main(String[] args){
		System.out.println("\nPlease enter your equation.\n");
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine(); // ((((90/30)-17)+((4*6)/2))+(8^2)) <-62
		System.out.println(Calculator.evaluate(input));
	}
}
