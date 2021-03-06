/*
Yikai Wang
APCS1 pd9
HW45 -- Come Together
2015-12-09
*/

public class SuperArray {
 
    //~~~~~INSTANCE VARS~~~~~
    //underlying container, or "core" of this data structure:
    private Comparable[] _data;

    //position of last meaningful value
    private int _lastPos;

    //size of this instance of SuperArray
    private int _size;

       
    //~~~~~METHODS~~~~~
    //default constructor – initializes 10-item array
    public SuperArray()
    {
    _data = new Comparable[10];
    _lastPos = -1; //flag to indicate no lastpos yet
    _size = 0;   
    }

       
    //output array in [a,b,c] format, eg
    // {1,2,3}.toString() -> "[1,2,3]"
    public String toString()
    {
    String foo = "[";
    for( int i = 0; i < _size; i++ ) {
        foo += _data[i] + ",";
    }
    //shave off trailing comma
    if ( foo.length() > 1 )
        foo = foo.substring( 0, foo.length()-1 );
    foo += "]";
    return foo;
    }

       
    //double capacity of this SuperArray
    private void expand()
    {
    Comparable[] temp = new Comparable[ _data.length * 2 ];
    for( int i = 0; i < _data.length; i++ )
        temp[i] = _data[i];
    _data = temp;
    }

       
    //accessor -- return value at specified index
    public Comparable get( int index ) { return _data[index]; }

       
    //mutator -- set value at index to newVal,
    //           return old value at index
    public Comparable set( int index, Comparable newVal )
    {
     Comparable temp = _data[index]; //preserves old value
    _data[index] = newVal;
    return temp; //returns old value
    }


    // ~~~~~~~~~~~~~~ PHASE II ~~~~~~~~~~~~~~
    //adds an item after the last item
    public void add( Comparable newVal ) {
    if (_size >= _data.length) { //if array is full
        expand();
    }
        _data[++_lastPos] = newVal;
        ++_size; //size increases by one 
    }
   
   
    //inserts an item at index
    //shifts existing elements to the right
    public void add( int index, Comparable newVal ) {
    if( index <= _lastPos ){ //does not add if index of new value is past _lastPos

        if( _lastPos >= _data.length - 1 ) { //if array is almost filled up
            expand();
        }

       Comparable temp = set( index, newVal ); //holds last val

       for(int i = index + 1; i <= _lastPos + 1; i++){//also increments _lastPos
          temp = set(i, temp); //temp is redefined after the right side is done
          //System.out.println(temp + " " + this);         
       }
       _lastPos++;
       ++_size;
    }
}
        
  
    //removes the item at index
    //shifts elements left to fill in newly-empted slot
    public void remove( int index ) {
       
    Comparable temp = set( _lastPos, null ); //holds last val

    for (int i = --_lastPos; i >= index; i--){ //also decrements _lastPos
        temp = set(i, temp); //temp is redefined after the right side is done
    }
    --_size;
    }
   
   
   
    //return number of meaningful items in _data
    public int size() {
        _size = _lastPos + 1; //index of last meaningful item + 1
        return _size;
    }

    public int linSearch( Comparable target ) {
      for( int i = 0; i < _size; i++ )
       {
          //System.out.println( _data[i] + "\n");
          //System.out.println( i );

         if ( _data[i].getClass() == target.getClass() ) { //same class?
            if( _data[i].compareTo(target) == 0 ) {
              return i; //once it finds the first element, returns index
            }
         }
       }
        return -1; //if none found
    }

    public boolean isSorted() {
      int i = 0; //to go through the array

      for(; i < _size - 1; ++i ) {
        double val = helpSort( _data[i] );
        double vlr = helpSort( _data[i + 1] );

        //System.out.println( val );

        if( val > vlr ) {//if the next value is less
          return false;
        }
        }

      return true;
    }

    public double helpSort( Comparable c ) {
      if ( c instanceof Rational ) { //if it's a rational
        Rational r = ( Rational ) c;
        return r.floatValue();
      }

      if ( c instanceof Binary ) {//binary
        Binary b = ( Binary ) c;
        return Binary.binToDec( b + "" );
      }

      if ( c instanceof Hexadecimal ) {//hex
        Hexadecimal h = ( Hexadecimal ) c;
        return Hexadecimal.hexToDec( h + "" );
      }

      throw new ClassCastException ( "\n_data contains a wrong variable type :(");
    }

    //main method for testing
    public static void main( String[] args )
    {
      SuperArray johnCena = new SuperArray();

      for( int i = 0; i < 10; ++i ) {
        //System.out.println( i );

        if( i < 3 ) {
          johnCena.add( new Rational( i, i + 1 ) );
        }
        else if( i < 7 ) {
          johnCena.add( new Binary( i ) );
        }
        else {
          johnCena.add( new Hexadecimal( i%2 ) );
        }
      }

      System.out.println( johnCena.linSearch( new Binary( 4 ) ) );//4
      System.out.println( johnCena.isSorted() ); //false

    /*
    SuperArray curtis = new SuperArray();
    System.out.println("Printing empty SuperArray curtis...");
    System.out.println(curtis);

    for( int i = 0; i < curtis._data.length; i++ ) {
        curtis.set(i,i*2);
        curtis._size++; //necessary bc no add() method yet
    }

    System.out.println("Printing populated SuperArray curtis...");
    System.out.println(curtis);

    System.out.println("testing get()...");
    for( int i = 0; i < curtis._size; i++ ) {
        System.out.print( "item at index" + i + ":\t" );
        System.out.println( curtis.get(i) );
    }

    System.out.println("Expanded SuperArray curtis:");
    curtis.expand();
    System.out.println(curtis);

    SuperArray mayfield = new SuperArray();
    System.out.println("Printing empty SuperArray mayfield...");
    System.out.println(mayfield);

      mayfield.add(5);
      mayfield.add(4);
      mayfield.add(3);
      mayfield.add(2);
      mayfield.add(1);
     
      System.out.println("Printing populated SuperArray mayfield...");
      System.out.println(mayfield);

      mayfield.remove(3);
      System.out.println("Printing SuperArray mayfield post-remove...");
      System.out.println(mayfield);
      mayfield.remove(3);
      System.out.println("Printing SuperArray mayfield post-remove...");
      System.out.println(mayfield);

      mayfield.add(3,99);
      System.out.println("Printing SuperArray mayfield post-insert...");
      System.out.println(mayfield);
      mayfield.add(2,88);
      System.out.println("Printing SuperArray mayfield post-insert...");
      System.out.println(mayfield);
      mayfield.add(1,77);
      System.out.println("Printing SuperArray mayfield post-insert...");
      System.out.println(mayfield);
    //*****INSERT ANY ADDITIONAL TEST CALLS HERE*****
      mayfield.add(10,77);
      System.out.println("Printing SuperArray mayfield post-insert...");
      System.out.println(mayfield); //should not change
    
    
    //******TESTING INTERFACE ListInt ******
    
    //should print basically the same things as mayfield 
      
      ListInt hello = new SuperArray();
      System.out.println("Printing SuperArray hello of variable-type ListInt");
      System.out.println(hello);

      hello.add(5);
      hello.add(4);
      hello.add(3);
      hello.add(2);
      hello.add(1);
      
      System.out.println("Printing populated SuperArray hello...");
      System.out.println(hello);
      
      hello.remove(3);
      System.out.println("Printing SuperArray hello post-remove...");
      System.out.println(hello);
      hello.remove(3);
      System.out.println("Printing SuperArray hello post-remove...");
      System.out.println(hello);

      hello.add(3,99);
      System.out.println("Printing SuperArray hello post-insert...");
      System.out.println(hello);
      hello.add(2,88);
      System.out.println("Printing SuperArray hello post-insert...");
      System.out.println(hello);
      hello.add(1,77);
      System.out.println("Printing SuperArray hello post-insert...");
      System.out.println(hello);
    */

    }//end main
       
}//end class
