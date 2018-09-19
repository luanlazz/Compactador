package BTW;

public class main {

	public static void main(String[] args) {
		    String example = "banana";
		    
		    String encode = BTW.encode(example);
		    System.out.println(encode);
		    String decode = BTW.decode(encode);
		    System.out.println(decode);
		}

}
