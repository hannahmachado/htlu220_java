package lab07;

import java.util.Stack;

public class Postfixer {

	public static double InfixEvaluator(String line){
		StringSplitter data = new StringSplitter(line);
		Stack<String> operators = new Stack<String>();
		Stack<Double> operands = new  Stack<Double>();
		while(data.hasNext()){
			String token= data.next();
			if(IsNumber(token)){
				double d= Double.parseDouble(token);
				operands.push(d);
			}
			else if(token.equals("("))
					operators.push(token);
			else if (token.equals(")")){
				if (operators.size() != 0){
				 while(operators.size() != 0 && (!operators.peek().equals("("))){
					String operator = operators.pop();
					double value2 = operands.pop();
					double value1= operands.pop();
					double result= Calculator(value1, value2, operator);
					operands.push(result);
				}
			 operators.pop();
				}
			}
			else if(IsOperator(token)){
				String CurrentOperator = token;
				while(operators.size()>0 &&(OrderOfOperator(CurrentOperator)<=OrderOfOperator(operators.peek()))){
					String operator = operators.pop();
					double value2 = operands.pop();
					double value1= operands.pop();
					double result= Calculator(value1, value2, operator);
					operands.push(result);
				}
				operators.push(CurrentOperator);
		    }		
	   	}
		while(operators.size()>0){
			String operator = operators.pop();
			double value2 = operands.pop();
			double value1= operands.pop();
			double result= Calculator(value1, value2, operator);
			operands.push(result);	
		}

		Double finalResult= operands.pop();
		//System.out.println(finalResult);
		return finalResult;
	}
	
	private static boolean IsNumber(String token){
	   try{
		   Double.parseDouble(token);
		   return true;
	   }
	   catch (Exception ex){
		return false;
	   }
	}
	private static boolean IsOperator(String token){
	   if (token.equals("(")||token.equals(")")||token.equals("*")
		   ||token.equals("/")||token.equals("+")||token.equals("-")||token.equals("^"))
		   return true;	   
	return false;
	}
	private static int OrderOfOperator(String token){ 
	  if (token.equals("^")){
			return 3;
	  }	
	  else if (token.equals("*")|| token.equals("/")){
			return 2;
	  }
	  else if (token.equals("+")|| token.equals("-")){
			return 1;
	  }
	  return -1;
	}
	
	private static double Calculator(double value1, double value2, String operator){
		if(operator.equals("+"))
			return (value1 + value2);
		else if(operator.equals("-"))
			return (value1 - value2);
		else if(operator.equals("*"))
			return (value1 * value2);
		else if(operator.equals("/"))
			return (value1 / value2);
		else if(operator.equals("^"))
			return (Math.pow(value1, value2));
		return 0;
	}
	
	public static String PostfixConvertor(String line){
		StringSplitter data = new StringSplitter(line);
		Stack<String> opt = new Stack<String>();
		String postFix = new String();
		
		while(data.hasNext()){
			String token= data.next();
			if(IsNumber(token)){
				postFix += token;
			}
		    if(token.equals("("))
				opt.push(token);
			else if (token.equals(")")){
			   if (opt.size() != 0){
				 while(opt.size() != 0 && !(opt.peek().equals("("))){
						postFix += opt.pop();
				 }
				opt.pop();
			   }
			 }
			else if(IsOperator(token)){
				if (opt.peek().equals("("))
					opt.push(token);
				else{
				String CurrentOperator = token;
					while((!opt.peek().equals("(")) && OrderOfOperator(CurrentOperator)<=OrderOfOperator(opt.peek())){
						postFix += opt.pop();
					}
					opt.push(CurrentOperator);
					//System.out.println(opt);
				}
		   // System.out.println(opt);
			}
		}	
		//System.out.println(postFix);
		return postFix;		
	}
	
	public static void main (String[] args){
		if (InfixEvaluator("10 + 2") != 12)
         System.err.println("test1 failed --> your answer should have been 12");
    
        if (InfixEvaluator("10 - 2") != 8)
         System.err.println("test1 failed --> your answer should have been 12");
    
        if (InfixEvaluator("100 * 2 + 12") != 212)
         System.err.println("test2 failed --> your answer should have been 212");

        if (InfixEvaluator("100 * ( 2 + 12 )") != 1400)
          System.err.println("test3 failed --> your answer should have been 1400");
     
       if (InfixEvaluator("100 * ( 2 + 12 ) / 14") != 100)
        System.err.println("test4 failed --> your answer should have been 100");
    
    System.out.println("Testing Done!!!");
    
    if (!PostfixConvertor(new String("(4+5)")).equals("45+"))
        System.err.println("test1 failed --> should have been 45+");
    
    if (!PostfixConvertor(new String("((4+5)*6)")).equals("45+6*"))
        System.err.println("test2 failed --> should have been 45+6*");
    
    if (!PostfixConvertor(new String("((4+((5*6)/7))-8)")).equals("456*7/+8-"))
        System.err.println("test3 failed --> should have been 456*7/+8-");
    
    if (!PostfixConvertor(new String("((4+5*(6-7))/8)")).equals("4567-*+8/"))
        System.err.println("test4 failed --> should have been 4567-*+8/");
    
    if (!PostfixConvertor(new String("(9+(8*7-(6/5^4)*3)*2)")).equals("987*654^/3*-2*+"))
        System.err.println("test5 failed --> should have been 987*654^/3*-2*+");
    
    System.out.println("Testing Done!!!");
   }
}