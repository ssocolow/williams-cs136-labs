import structure5.Assert;
import structure5.List;
import structure5.StackList;

/**
 * An implementation of a basic PostScript interpreter.
 * The interpreter contains a stacklist to hold the execution stack
 * and a symboltable to hold the relevant symbols and procedures
 * the interpret function takes a reader and executes all the tokens that reader gives
 * main function reads from stdin
 */
public class Interpreter {
	private StackList<Token> stack;
	private SymbolTable table;

	/**
	 * constructs an Interpreter by initializing the stacklist and symboltable
	 */
	public Interpreter() {
		stack = new StackList<Token>();
		table = new SymbolTable();
	}

	/**
	 * Uses a reader to get tokens to perform actions with
	 * @param r a Reader to provide tokens to interpret
	 */
	public void interpret(Reader r) {
		Token t;
		while (r.hasNext()) {
			t = r.next();
			if (t.isSymbol() && t.getSymbol().equals("quit")) {
				break;
			} else if (t.isNumber()) {
				push(t);
			} else if (t.isBoolean()) {
				push(t);
			} else if (t.isSymbol() && t.getSymbol().equals("pstack")) {
				pstack();
			} else if (t.isSymbol() && t.getSymbol().equals("pop")) {
				pop();
			} else if (t.isSymbol() && t.getSymbol().equals("add")) {
				add();
			} else if (t.isSymbol() && t.getSymbol().equals("mul")) {
				mul();
			} else if (t.isSymbol() && t.getSymbol().equals("sub")) {
				sub();
			} else if (t.isSymbol() && t.getSymbol().equals("div")) {
				div();
			} else if (t.isSymbol() && t.getSymbol().equals("dup")) {
				dup();
			} else if (t.isSymbol() && t.getSymbol().equals("exch")) {
				exch();
			} else if (t.isSymbol() && t.getSymbol().equals("eq")) {
				eq();
			} else if (t.isSymbol() && t.getSymbol().equals("ne")) {
				ne();
			} else if (t.isSymbol() && t.getSymbol().equals("lt")) {
				lt();
			} else if (t.isSymbol() && t.getSymbol().equals("def")) {
				def();
			} else if (t.isSymbol() && t.getSymbol().equals("ptable")) {
				ptable();
			} else if (t.isSymbol() && t.getSymbol().equals("if")) {
				ifFunction();
			} else if (t.isSymbol() && table.contains(t.getSymbol())) {
				pushFromSymbol(table.get(t.getSymbol()));
			} else {
				push(t);
			}
		}
	}
	/**
	 * pops the top two elements from the stack
	 * first element is a token
	 * second is a boolean
	 * if boolean is true, token is executed
	 * @pre stack must have at least two elements
	 */
	public void ifFunction() {
		Assert.pre(stack.size() > 1, "stack must have at least two elements");
		Token t1 = pop();
		Token t2 = pop();
		Assert.pre(t2.isBoolean(), "t2 has to be a boolean");

		if (t2.getBoolean()) {
			Reader r2 = new Reader(t1);
			interpret(r2);
		}
	}
	/**
	 * prints the current symbol table
	 */
	public void ptable() {
		System.out.print(table);
	}
	/**
	 * defines a symbol or a procedure that has a backslash
	 * adds it to the symbol table
	 * @pre must have a backslash in the definition and must be a symbol
	 * @pre stack must have at least two elements
	 */
	public void def() {
		Assert.pre(stack.size() > 1, "stack must have at least two elements");
		Token val = stack.pop();
		Token name = stack.pop();
		Assert.pre(name.isSymbol() && name.getSymbol().contains("/"), "name must be string and have /");
		table.add(name.getSymbol().substring(1, name.getSymbol().length()), val);
	}
	
	/**
	 * tests whether the second element is less than the first
	 * and puts the boolean result on the stack
	 * @pre stack must have at least two elements
	 * @pre only works for Strings and numbers
	 */
	public void lt() {
		Assert.pre(stack.size() > 1, "stack must have at least two elements");
        Token t1 = pop();
		Token t2 = pop();

		if (t1.isNumber() && t2.isNumber()) {
			if (t2.getNumber() < t1.getNumber()) {
				push(new Token(true));
			} else {
				push(new Token(false));
			}
		} else if (t1.isSymbol() && t2.isSymbol()) {
			if (t2.getSymbol().compareTo(t1.getSymbol()) < 0) {
				push(new Token(true));
			} else {
				push(new Token(false));
			}
		} else {
			Assert.fail("not both are numbers or strings");
		}
	}
	/**
	 * tests whether the first two elements are not equal by popping them off
	 * and puts the boolean result on the stack
	 * @pre stack must have at least two elements
	 */
	public void ne() {
		Assert.pre(stack.size() > 1, "stack must have at least two elements");
        Token t1 = pop();
		Token t2 = pop();

		if (!t1.equals(t2)) {
			push(new Token(true));
		} else {
			push(new Token(false));
		}
	}
	/**
	 * tests whether the first two elements are equal by popping them off
	 * and puts the boolean result on the stack
	 * @pre stack must have at least two elements
	 */
	public void eq() {
		Assert.pre(stack.size() > 1, "stack must have at least two elements");
        Token t1 = pop();
		Token t2 = pop();

		if (t1.equals(t2)) {
			push(new Token(true));
		} else {
			push(new Token(false));
		}
	}

	/**
	 * exchanges the top element of the stack with the bottom
	 * might have problem where they are the same reference?
	 * @pre stack must have at least two elements
	 */
	public void exch() {
		Assert.pre(stack.size() > 1, "stack must have at least two elements");
        Token t1 = pop();
		Token t2 = pop();
		push(t1);
		push(t2);
	}

	/**
	 * duplicates the top element of the stack by popping it and them pushing it twice
	 * might have problem where they are the same reference?
	 * @pre stack must not be empty
	 */
	public void dup() {
		Assert.pre(!stack.isEmpty(), "stack can't be empty");
		Token t = pop();
		push(t);
		push(t);
	}
	/**
	 * pops the first two elements off the stack,
	 * divides them,
	 * and pushes the quotient back onto the stack
	 * @pre both tokens are numbers
	 * @pre stack must have at least two elements
	 */
	public void div() {
		Assert.pre(stack.size() > 1, "stack must have at least two elements");
		Token t1 = pop();
		Token t2 = pop();
		Assert.pre(t1.isNumber() && t2.isNumber(), "both tokens have to be numbers");
		Token t3 = new Token(t2.getNumber() / t1.getNumber());
		push(t3);
	}

	/**
	 * pops the first two elements off the stack,
	 * subtracts them,
	 * and pushes the difference back onto the stack
	 * @pre both tokens are numbers
	 * @pre stack must have at least two elements
	 */
	public void sub() {
		Assert.pre(stack.size() > 1, "stack must have at least two elements");
		Token t1 = pop();
		Token t2 = pop();
		Assert.pre(t1.isNumber() && t2.isNumber(), "both tokens have to be numbers");
		Token t3 = new Token(t2.getNumber() - t1.getNumber());
		push(t3);
	}

	/**
	 * pops the first two elements off the stack,
	 * multiplies them,
	 * and pushes the product back onto the stack
	 * @pre both tokens are numbers
	 * @pre stack must have at least two elements
	 */
	public void mul() {
		Assert.pre(stack.size() > 1, "stack must have at least two elements");
		Token t1 = pop();
		Token t2 = pop();
		Assert.pre(t1.isNumber() && t2.isNumber(), "both tokens have to be numbers");
		Token t3 = new Token(t1.getNumber() * t2.getNumber());
		push(t3);
	}

	/**
	 * pops the first two elements off the stack,
	 * adds them,
	 * and pushes the sum back onto the stack
	 * @pre both tokens are numbers
	 * @pre stack must have at least two elements
	 */
	public void add() {
		Assert.pre(stack.size() > 1, "stack must have at least two elements");
		Token t1 = pop();
		Token t2 = pop();
		Assert.pre(t1.isNumber() && t2.isNumber(), "both tokens have to be numbers");
		Token t3 = new Token(t1.getNumber() + t2.getNumber());
		push(t3);
	}

	/**
	 * removes the top element of the stack
	 * @return the top element of the stack
	 * @pre stack is not empty
	 */
	public Token pop() {
		Assert.pre(!stack.isEmpty(), "stack can't be empty when popping");
		return stack.pop();
	}
	/**
	 * interprets full procedure on stack if procedure
	 * @param t is Token to be added to stack
	 */
	public void pushFromSymbol(Token t) {
		if (!t.isProcedure()) {
			stack.add(t);
		} else {
			List<Token> l = t.getProcedure();
			Reader r2 = new Reader(l);
			interpret(r2);
		}
	}
	/**
	 * adds a token to the stack
	 * @param t is Token to be added to stack
	 */
	public void push(Token t) {
		stack.add(t);
	}
	
	/**
	 * prints the stack using a temp stack
	 * prints a newline if stack is empty
	 */
	public void pstack() {
		//make a temp stack
		StackList<Token> temp = new StackList<>();
		//store size of stack
		int s = stack.size();

		//loop through the stack and pop off elements and print them and add them to temp stack
		for (int i = 0; i < s; i++) {
			Token t = stack.pop();
			System.out.println(t);
			temp.add(t);
		}

		//loop through temp and pop elements back into stack
		for (int i = 0; i < s; i++) {
			stack.add(temp.pop());
		}

		if (stack.isEmpty()) {
			System.out.println();
		}
	}

	/**
	 * makes a new interpreter and sets up a new default reader and interprets it
	 * by default it uses stdin
	 * @param args
	 */
	public static void main(String[] args) {
		Interpreter i = new Interpreter();
		Reader r = new Reader();
		i.interpret(r);
	}
}