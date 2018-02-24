package msh.gstreport.test;

public class Test {
	public static void main(String[] args) {
		String str="3,240.00";
		System.out.println("[31m"+str.replaceAll(",", ""));
		 System.out.println("\033[31;1mHello\033[0m, \033[32;1;2mworld!\033[0m");
		    System.out.println("\033[31mRed\033[32m, Green\033[33m, Yellow\033[34m, Blue\033[0m");
	}
}
