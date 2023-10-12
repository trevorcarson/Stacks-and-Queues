/*
A class for evaluating expressions in prefix and postfix notation
*/

public class PrefixPostfix{
   public static LQueue tokenize(String exp){
      /*Split a String containing mathematical expressions into
      a series of tokens. All operators and operands must be separated
      by whitespace.
      Input:
         String exp: a String representing a mathematical expression
      Output:
         return: a Queue containing the split tokens
      Ex.
      tokenize("+ 3 4") -> ["+", "3", "4"]
      tokenize("* -2.5 - 4.13 9") -> ["*", "-2.5", "-", "4.13", "9"]
      */
      LQueue tokens = new LQueue();
      String token = "";
      boolean inToken = true; //flag to delete whitespace
      for(int i = 0; i < exp.length(); i++){
         char c = exp.charAt(i);
         if(inToken == true){ //currently reading a token
            if(c == ' '){
               inToken = false;
               tokens.add(token);
               token = "";
            }
            else{
               token += c;
            }
         }
         else{//skipping white space
            if(c != ' '){
               inToken = true;
               token += c;
            }
         }
      }
      if(token.equals("") == false){//if the string ended on a token
         tokens.add(token);
      }
      return tokens;
   }
   
   public static boolean isOperator(String token){
      /*Check if the given token is a valid arithmetic operator
      Input:
         String token: the token to check
      Output:
         return: true if token is a valid arithmetic operator
      Ex.
      isOperator("*") -> true
      isOperator("^") -> true
      isOperator("1.5") -> false
      */
      if(token.equals("+")){
         return true;
      }
      if(token.equals("-")){
         return true;
      }
      if(token.equals("*")){
         return true;
      }
      if(token.equals("/")){
         return true;
      }
      if(token.equals("^")){
         return true;
      }
      return false;
   }
   public static double arithmetic(String operator, double operand1, double operand2){
      /*Evaluate basic arithmetic operations
      Input:
         String operator: a String representing a supported 
                          arithmetic operation:
                          +: addition
                          -: subtraction
                          *: multiplication
                          /: division
                          ^: power
         double operand1: the 1st operand
         double operand2: the 2nd operand
      Output:
         return: the result of the operation
      Ex.
      arithmetic("+", 1, 2) -> 3.0
      arithmetic("-", 7, 6) -> 1.0
      arithmetic("*", 1.5, 2) -> 3.0
      arithmetic("/", 4, 5) -> 0.8
      arithmetic("^", 2, 3) -> 8.0
      */
      if(operator.equals("+")){
         return operand1 + operand2;
      }
      else if(operator.equals("-")){
         return operand1 - operand2;
      }
      else if(operator.equals("*")){
         return operand1 * operand2;
      }
      else if(operator.equals("/")){
         return operand1 / operand2;
      }
      else if(operator.equals("^")){
         return Math.pow(operand1, operand2);
      }
      else{
         throw new IllegalArgumentException("unsupported operator: "+operator);
      }
   }
   public static void queueToStack(LQueue q, LStack s){
      while(!q.isEmpty()){
         Object n = q.peek();
         s.push(n);
         q.remove();
      }
   }
   public static void stackToQueue(LStack s, LQueue q){
      while(s.peek() != null){
         Object a = s.peek();
         q.add(a);
         s.pop();
      }
   }
   public static double evalPrefix(String exp){
      /*Evaluate an expression written in prefix notation
      Input:
         String exp: an arithmetic expression in prefix notation
      Output:
         return: the calculated value of the expression
      Ex.
      evalPrefix("+ 1 2") -> 3.0
      evalPrefix("- 3 4") -> -1.0
      evalPrefix("* 2 - 3 4) -> -2.0
      evalPrefix("* + 8 3 / 5 - 7 2") -> 11.0
      */
      LQueue q = tokenize(exp);
      LStack s = new LStack();
      queueToStack(q,s);
      while(s.size() >= 3){
         if(q.isEmpty()==false){
            s.push(q.remove());
         }
         Object a = s.pop();
         Object b = s.pop();
         Object c = s.pop();
         if((!isOperator("" + a)) && (!isOperator("" + b))
            && (isOperator("" + c))) {
               double B = Double.valueOf(b.toString());
               double A = Double.valueOf(a.toString());
               String S = (String) c;
               Object T = arithmetic(S,B,A);
               s.push(T);
         }
         else{
            q.add(a);
            s.push(c);
            s.push(b);
            Object k = s.pop();
            Object l = s.pop();
            Object y = s.pop();
            if((!isOperator("" + k)) && (!isOperator("" + l))
            && (isOperator("" + y))) {
               double L = Double.valueOf(l.toString());
               double K = Double.valueOf(k.toString());
               String W = (String) y;
               Object N = arithmetic(W,K,L);
               s.push(N);
            }
         }
      }
      return Double.valueOf(s.peek().toString()); 
   }
   public static void main(String [] args){
      StdOut.println(evalPrefix("* + 8 3 / 5 - 7 2"));
   }   
   public static double Prefix(String exp){
      LQueue q = tokenize(exp);
      LStack s = new LStack();
      s.push(q.remove());
      s.push(q.remove());
      s.push(q.remove());
      while((s.size() > 1) || (q.size() > 0)){
         Object a = s.pop();
         Object b = s.pop();
         Object c = s.pop();
         if((!isOperator("" + a)) && (!isOperator("" + b))
            && (isOperator("" + c))) {
               double B = Double.valueOf(b.toString());
               double A = Double.valueOf(a.toString());
               String S = (String) c;
               Object T = arithmetic(S,B,A);
               s.push(T);
         }
         else{
            s.push(q.remove());
         }
      }
      return Double.valueOf(s.peek().toString());
   }
}