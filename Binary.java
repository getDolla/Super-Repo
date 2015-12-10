/*
Yikai Wang
APCS1 pd9
HW45 -- Come Together
2015-12-09
*/ 

//skeleton file for class Binary

public class Binary implements Comparable {

    private int _decNum;
    private String _binNum;


    /*=====================================
      default constructor
      pre:  n/a
      post: initializes _decNum to 0, _binNum to "0"
      =====================================*/
    public Binary() { 
    /****** YOUR IMPLEMENTATION HURRR ******/
    _decNum = 0;
    _binNum = "0";   
    }


    /*=====================================
      overloaded constructor
      pre:  n >= 0
      post: sets _decNum to n, _binNum to equiv string of bits
      =====================================*/
    public Binary( int n ) {
    /****** YOUR IMPLEMENTATION HURRR ******/
    _decNum = n;
    _binNum = decToBin( n );   
    }


    /*=====================================
      overloaded constructor
      pre:  s is String representing non-negative binary number
      post: sets _binNum to input, _decNum to decimal equiv
      =====================================*/
    public Binary( String s ) {
    /****** YOUR IMPLEMENTATION HURRR ******/
    _binNum = s;
    _decNum = binToDec( s );   
    }


    /*=====================================
      String toString() -- returns String representation of this Object
      pre:  n/a
      post: returns String of 1's and 0's representing value of this Object
      =====================================*/
    public String toString() { 
    /****** YOUR IMPLEMENTATION HURRR ******/
    return _binNum;   
    }


    /*=====================================
      String decToBin(int) -- converts base-10 input to binary
      pre:  n >= 0
      post: returns String of bits
      eg  decToBin(0) -> "0"
      decToBin(1) -> "1"
      decToBin(2) -> "10"
      decToBin(3) -> "11"
      decToBin(14) -> "1110"
      =====================================*/
    public static String decToBin( int n ) {
    /****** YOUR IMPLEMENTATION HURRR ******/
    if( n > 0 ) { //if the number isn't zero, so there won't be extra 0s at the end
    String s = "";

    while( n != 0 ) {
        s = ( n % 2 ) + "" + s; //adds the remainders to the left side
        n /= 2;
    }
    return s;
    }
    return "0";
    }


    /*=====================================
      String decToBinR(int) -- converts base-10 input to binary, recursively
      pre:  n >= 0
      post: returns String of bits
      eg  decToBinR(0) -> "0"
      decToBinR(1) -> "1"
      decToBinR(2) -> "10"
      decToBinR(3) -> "11"
      decToBinR(14) -> "1110"
      =====================================*/
    public static String decToBinR( int n ) { 
    /****** YOUR IMPLEMENTATION HURRR ******/
    if( n == 0 )
      {
        return "0";
      }
    return decToBinR( n / 2 ) + "" + ( n % 2 );//adds remainders to the left   
    }


    /*=====================================
      int binToDec(String) -- converts base-10 input to binary
      pre:  s represents non-negative binary number
      post: returns decimal equivalent of int
      eg  
      binToDec("0") -> 0
      binToDec("1") -> 1
      binToDec("10") -> 2
      binToDec("11") -> 3
      binToDec("1110") -> 14
      =====================================*/
    public static int binToDec( String s ) {
    /****** YOUR IMPLEMENTATION HURRR ******/
       int i = 0;
       for( int n = s.length() - 1; n >= 0; --n ) {
        int pow = s.length() - 1 - n; //for exponent
        i+= (int) Math.pow( 2, pow ) * Integer.parseInt( s.substring(n, n + 1 ) );//s[n] * 2^pow
       }
       return i;
    }


    /*=====================================
      int binToDecR(String) -- converts base-10 input to binary, recursively
      pre:  s represents non-negative binary number
      post: returns decimal equivalent of int
      eg  
      binToDecR("0") -> 0
      binToDecR("1") -> 1
      binToDecR("10") -> 2
      binToDecR("11") -> 3
      binToDecR("1110") -> 14
      =====================================*/
    public static int  binToDecR( String s ) { 
    /****** YOUR IMPLEMENTATION HURRR ******/
      if( s.length() == 1 )//if it's a single digit ( 0 or 1 )
      {
        return Integer.parseInt( s );
      }
      int n = s.length() - 1;//gets exponent
      int val = Integer.parseInt( s.substring( 0, 1 ) );
      return (int) Math.pow( 2, n ) * val + binToDecR( s.substring( 1 ) );
    }


    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Binary
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal binary values
      =============================================*/
    public boolean equals( Object other ) { 
    /****** YOUR IMPLEMENTATION HURRR ******/
    return compareTo( other ) == 0;   
    }


    /*=============================================
      int compareTo(Object) -- tells which of two Binary objects is greater
      pre:  other is instance of class Binary
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
      =============================================*/
    public int compareTo( Object o ) {
    /****** YOUR IMPLEMENTATION HURRR ******/
    if ( o == null ) {
    throw new NullPointerException( "\ncompareTo() input is a null :(" ); 
    }

    if (! ( o instanceof Binary )) {//if they are not the same object
      throw new ClassCastException ( "\ncompareTo() input is not a Binary :(");
    }

      Binary obj = (Binary) o;
      if( (this._decNum == obj._decNum) || (this._binNum.equals(obj._binNum)) ) {
        return 0;//same values
      }

      int val = binToDec( obj._binNum );
      if(( this._decNum > obj._decNum ) || ( this._decNum > val )) {
        return 1;//if the other object is bigger
      }
    return -1;
    }


    //main method for testing
    public static void main( String[] args ) {

    System.out.println();
    System.out.println( "Testing ..." );

    Binary b1 = new Binary(5);
    Binary b2 = new Binary(5);
    Binary b3 = b1;
    Binary b4 = new Binary(7);

    System.out.println( b1 );//101
    System.out.println( b2 );//101
    System.out.println( b3 );//101     
    System.out.println( b4 );//111       

    System.out.println( "\n==..." );
    System.out.println( b1 == b2 ); //should be false
    System.out.println( b1 == b3 ); //should be true

    System.out.println( "\n.equals()..." );
    System.out.println( b1.equals(b2) ); //should be true
    System.out.println( b1.equals(b3) ); //should be true
    System.out.println( b3.equals(b1) ); //should be true
    System.out.println( b4.equals(b2) ); //should be false
    System.out.println( b1.equals(b4) ); //should be false

    System.out.println( "\n.compareTo..." );
    System.out.println( b1.compareTo(b2) ); //should be 0
    System.out.println( b1.compareTo(b3) ); //should be 0
    System.out.println( b1.compareTo(b4) ); //should be neg
    System.out.println( b4.compareTo(b1) ); //should be pos

    }//end main()

} //end class