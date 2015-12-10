/*
Yikai Wang
APCS1 pd9
HW45 -- Come Together
2015-12-09
*/ 
 
//skeleton file for class hexadecimal 
 
public class Hexadecimal implements Comparable { 
    private final static String HEXDIGITS= "0123456789ABCDEF";
    private int _decNum; 
    private String _hexNum; 
 
 
    /*===================================== 
      default constructor 
      pre:  n/a 
      post: initializes _decNum to 0, _hexNum to "0" 
      =====================================*/ 
    public Hexadecimal() {  
    /****** YOUR IMPLEMENTATION HURRR ******/ 
    _decNum = 0; 
    _hexNum = "0";    
    } 
 
 
    /*===================================== 
      overloaded constructor 
      pre:  n >= 0 
      post: sets _decNum to n, _hexNum to equiv string of bits 
      =====================================*/ 
    public Hexadecimal( int n ) { 
    /****** YOUR IMPLEMENTATION HURRR ******/ 
    _decNum = n; 
    _hexNum = decToHex( n );    
    } 
 
 
    /*===================================== 
      overloaded constructor 
      pre:  s is String representing non-negative hexadecimal number 
      post: sets _hexNum to input, _decNum to decimal equiv 
      =====================================*/ 
    public Hexadecimal( String s ) { 
    /****** YOUR IMPLEMENTATION HURRR ******/ 
    _hexNum = s; 
    _decNum = hexToDec( s );    
    } 
 
 
    /*===================================== 
      String toString() -- returns String representation of this Object 
      pre:  n/a 
      post: returns String of 1's and 0's representing value of this Object 
      =====================================*/ 
    public String toString() {  
    /****** YOUR IMPLEMENTATION HURRR ******/ 
    return _hexNum;    
    } 
 
 
    /*===================================== 
      String decToHex(int) -- converts base-10 input to hexadecimal 
      pre:  n >= 0 
      post: returns String of bits 
      eg  decToHex(0) -> "0" 
      decToHex(1) -> "1" 
      decToHex(2) -> "10" 
      decToHex(3) -> "11" 
      decToHex(14) -> "1110" 
      =====================================*/ 
    public static String decToHex( int n ) { 
    /****** YOUR IMPLEMENTATION HURRR ******/ 
    if( n > 0 ) { //if the number isn't zero, so there won't be extra 0s at the end 
    String s = ""; 
 
    while( n != 0 ) { 
        s = HEXDIGITS.charAt(n%16) + "" + s; //adds the remainders to the left side 
        n /= 16; 
    } 
    return s; 
    } 
    return "0"; 
    } 
 
    /*===================================== 
      String decToHexR(int) -- converts base-10 input to hexadecimal, recursively 
      pre:  n >= 0 
      post: returns String of bits 
      eg  decToHexR(0) -> "0" 
      decToHexR(1) -> "1" 
      decToHexR(2) -> "10" 
      decToHexR(3) -> "11" 
      decToHexR(14) -> "1110" 
      =====================================*/ 
    public static String decToHexR( int n ) {  
    /****** YOUR IMPLEMENTATION HURRR ******/ 
    if( n == 0 ) 
      { 
        return "0"; 
      } 
    return decToHexR( n / 16 ) + "" + HEXDIGITS.charAt( n % 16 );//adds remainders to the left    
    } 
 
 
    /*===================================== 
      int hexToDec(String) -- converts base-10 input to hexadecimal 
      pre:  s represents non-negative hexadecimal number 
      post: returns decimal equivalent of int 
      eg   
      hexToDec("0") -> 0 
      hexToDec("1") -> 1 
      hexToDec("10") -> 2 
      hexToDec("11") -> 3 
      hexToDec("1110") -> 14 
      =====================================*/ 
    public static int hexToDec( String s ) { 
    /****** YOUR IMPLEMENTATION HURRR ******/ 
       int i = 0; 
       for( int n = s.length() - 1; n >= 0; --n ) { 
        int pow = s.length() - 1 - n; //for exponent 
        i+= (int) Math.pow( 16, pow ) * HEXDIGITS.indexOf(s.substring(n, n + 1 ) );//s[n] * 2^pow 
       } 
       return i; 
    } 
 
 
    /*===================================== 
      int hexToDecR(String) -- converts base-10 input to hexadecimal, recursively 
      pre:  s represents non-negative hexadecimal number 
      post: returns decimal equivalent of int 
      eg   
      hexToDecR("0") -> 0 
      hexToDecR("1") -> 1 
      hexToDecR("10") -> 2 
      hexToDecR("11") -> 3 
      hexToDecR("1110") -> 14 
      =====================================*/ 
    public static int hexToDecR( String s ) {  
    /****** YOUR IMPLEMENTATION HURRR ******/ 
      if( s.length() == 1 )//if it's a single digit ( 0 or 1 ) 
      { 
	  return HEXDIGITS.indexOf(s); 
      } 
      int n = s.length() - 1;//gets exponent 
      int val = HEXDIGITS.indexOf(s.substring( 0, 1 ) ); 
      return (int) Math.pow( 16, n ) * val + hexToDecR( s.substring( 1 ) ); 
    } 
 
 
    /*============================================= 
      boolean equals(Object) -- tells whether 2 Objs are equivalent 
      pre:  other is an instance of class hexadecimal 
      post: Returns true if this and other are aliases (pointers to same  
      Object), or if this and other represent equal hexadecimal values 
      =============================================*/
    public boolean equals( Object other ) {  
    /****** YOUR IMPLE*/
    return compareTo( other ) == 0;    
    } 
 
    /*============================================= 
      int compareTo(Object) -- tells which of two hexadecimal objects is greater 
      pre:  other is instance of class hexadecimal 
      post: Returns 0 if this Object is equal to the input Object, 
      negative integer if this<input, positive integer otherwise 
      =============================================*/
    public int compareTo( Object o ) { 
    /****** YOUR IMPLEMENTATION HURRR ******/
    if ( o == null ) {
    throw new NullPointerException( "\ncompareTo() input is a null :(" ); 
    }

    if ( !(o instanceof Hexadecimal )) {//if they are not the same object
      throw new ClassCastException ( "\ncompareTo() input is not a Hexadecimal :(");
    }

      Hexadecimal obj = (Hexadecimal) o; 
      if( (this._decNum == obj._decNum) || (this._hexNum.equals(obj._hexNum)) ) { 
        return 0;//same values 
      }

      int val = hexToDec( obj._hexNum ); 
      if(( this._decNum > obj._decNum ) || ( this._decNum > val )) { 
        return 1;//if the calling object is bigger 
      } 

      return -1; 
    } 
 
    //main method for testing 
    public static void main( String[] args ) { 
 
    System.out.println(); 
    System.out.println( "Testing ..." ); 
 
    Hexadecimal b1 = new Hexadecimal(123); 
    Hexadecimal b2 = new Hexadecimal(15); 
    Hexadecimal b3 = b1; 
    Hexadecimal b4 = new Hexadecimal(31); 
 
    System.out.println( b1 );//7B
    System.out.println( b2 );//F 
    System.out.println( b3 );//7B      
    System.out.println( b4 );//1F        
 
    System.out.println( "\n==..." ); 
    System.out.println( b1 == b2 ); //should be false 
    System.out.println( b1 == b3 ); //should be true 
 
    System.out.println( "\n.equals()..." ); 
    System.out.println( b1.equals(b2) ); //should be true 
    System.out.println( b1.equals(b3) ); //should be true 
    System.out.println( b3.equals(b1) ); //should be true 
    System.out.println( b4.equals(b2) ); //should be false 
    System.out.println( b1.equals(b4) ); //should be false

    System.out.println("\n" + hexToDecR("3E8"));//1000 

    System.out.println( "\n.compareTo..." ); 
    System.out.println( b1.compareTo(b2) ); //should be 1 
    System.out.println( b1.compareTo(b3) ); //should be 0 
    System.out.println( b1.compareTo(b4) ); //should be 1 
    System.out.println( b4.compareTo(b1) ); //should be -1 
    
    Object obj = new Object();
    System.out.println( b1.compareTo(obj) );//should give exception
    }//end main() 
 
} //end class
