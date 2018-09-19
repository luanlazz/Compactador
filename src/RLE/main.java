package RLE;

public class main {

	public static void main(String[] args) {
		    String example = "aaaabbbbbcccccr";
		    
		    String encode = RLE.encode(example);
		    System.out.println(encode);
		    String decode = RLE.decode(encode);
		    System.out.println(decode);
		}

}
