/*A minimal Linked List implementation*/

public class LList{
   private LNode start;   //the first node in the Linked List
   
   public LList(){
      /*Constructor, initialize a new empty list*/
      this.start = null;
   }
   
   public String toString(){
      /*Return a String representing the contents of this list*/
      if(this.start == null){
         return "null";
      }
      else{
         return this.start.toString();
      }
   }
   
   public void add(Object o){
      /*
      Add o to the end of the list
      Inputs:
         this: the LList to add to
         o: the data to add to the list
      Results:
         Side Effects: a new node containing o has been added to
                       the end of the list
      Ex.
      LList l = new LList()
      l.toString() -> "null"
      l.add(1)
      l.toString() -> "1 -> null"
      l.add(2)
      l.toString() -> "1 -> 2 -> null"
      l.add(3)
      l.toString() -> "1 -> 2 -> 3 -> null"
      */
      LNode newNode = new LNode(o); //put data in a node
      if(this.start == null){//special case for first item in list
         this.start = newNode;
      }
      else{//otherwise, find the end of the list
         LNode end = this.start;
         while(end.getNext() != null){
            end = end.getNext();
         }
         end.setNext(newNode);//tack the new node onto the end
      }
   }
   public Object get(int index){
      /*
      Get the data at index. If index is out of bounds may
      result in an unprotected error
      Inputs:
         this: the LList we are checking
         index: the index in the list to check
      Results:
         return: the data (not the node) at index
         Side Effects: none, the list is unchanged
      Ex.
      LList l = new LList()
      l.add(1)
      l.add(2)
      l.add(3)
      l.toString() -> "1 -> 2 -> 3 -> null"
      l.get(0) -> 1
      l.get(1) -> 2
      l.get(2) -> 3
      l.get(3) -> ERROR (possibly)
      l.toString() -> "1 -> 2 -> 3 -> null"
      */
      int i = 0;
      LNode answer = this.start;
      while(i != index){
         answer = answer.getNext();
         i++;
      }
      return answer.getData();
   }
   public Object remove(int index){
      /*
      Removes the data at index from the list and 
      returns what was there. Index must be in-bounds
      or it may trigger an error.
      Input:
         this: the list you are looking in
         index: the index of the item to remove
      Results:
         return: return the data that was at index
         Side Effects: that node has been removed from the list
      Ex.
      LList l = new LList()
      l.add(1)
      l.add(2)
      l.add(3)
      l.toString() -> "1 -> 2 -> 3 -> null"
      l.remove(1) -> 2
      l.toString() -> "1 -> 3 -> null"
      l.remove(1) -> 3
      l.toString() -> "1 -> null"
      l.remove(0) -> 1
      l.toString() -> "null"
      l.remove(0) -> ERROR (possibly)
      */
      LNode x = this.start;
      if(index == 0){
         Object t = x.getData();
         this.start = this.start.getNext();
         return t;
      }
      else{
         for(int i = 0; i < index-1; i++){
            x = x.getNext();
         }
         LNode y = x.getNext();
         Object t = y.getData();
         x.setNext(y.getNext());
         return t;
      }
   }
   public void insert(Object o, int index){
      /*
      Adds new data into the list at the specified index.
      Does not destroy any existing data, but merely shifts
      it out of the way. Index should be in-bounds+1 or an error
      may occur.
      Inputs:
         this: the list you are adding to
         o: the data to be added to the list
         index: the index where we want to insert (in-bounds+1)
      Results:
         Side Effects: this list now contains o at index
      Ex.
      LList l = new LList()
      l.insert(1, 0)
      l.toString() -> "1 -> null"
      l.insert(2, 0)
      l.toString() -> "2 -> 1 -> null"
      l.insert(3, 1)
      l.toString() -> "2 -> 3 -> 1 -> null"
      l.insert(4, 4) -> ERROR (possibly)
      */
      LNode prev = this.start;
      LNode add = new LNode(o);
      if(index == 0){
         add.setNext(this.start);
         this.start = add;
      }
      else{
         int i = 0;
         while(i < index-1){
            prev = prev.getNext();
            i++;
         }
         add.setNext(prev.getNext());
         prev.setNext(add);
      }
   }
   public void set(Object o, int index){
      /*
      Replace the value at index with o. Must be an index
      within the current bounds of the list. Should not use
      any other helper methods previously coded like add(),
      get(), remove() etc. as this would be inefficient.
      Inputs:
         this: the list you want to modify
         o: the new data to put in the list
         index: where to put this data (must be in-bounds)
      Results:
         Side Effects: the previous item at index is replaced by o
      Ex.
      LList l = new LList()
      l.add(1)
      l.add(2)
      l.add(3)
      l.toString() -> "1 -> 2 -> 3 -> null"
      l.set(4, 1)
      l.toString() -> "1 -> 4 -> 3 -> null"
      l.set(5, 3) -> ERROR (possibly)
      */
      LNode add = new LNode (o);
      LNode prev = this.start;
      for(int i = 0; i < index-1; i++){
         prev = prev.getNext();
      }
      add.setNext(prev.getNext().getNext());
      prev.setNext(add);
   }
   public void clear(){
      /*
      Empties the list of all items.
      Inputs:
         this: the list to empty
      Results:
         Side Effects: the list is now empty with nothing in it
      Ex.
      LList l = new LList()
      l.add(1)
      l.add(2)
      l.add(3)
      l.toString() -> "1 -> 2 -> 3 -> null"
      l.clear()
      l.toString() -> "null"
      */
      this.start = null;
   }
   public int size(){
      /*
      Return the number of items in the list,
      a.k.a. the size of the list.
      Inputs:
         this: the list whose size you want to check
      Results:
         return: the number of items in the list
      Ex.
      LList l = new LList()
      l.size() -> 0
      l.add(1)
      l.size() -> 1
      l.add(2)
      l.size() -> 2
      l.add(3)
      l.size() -> 3
      l.remove(0)
      l.size() -> 2
      */
      LNode count = this.start;
      int i = 0;
      while(count != null){
         count = count.getNext();
         i++;
      }
      return i;
   }
   public boolean contains(Object o){
      /*
      Check if this list contains o. Compares
      by value, not by memory location, i.e. uses
      .equals(), not == .
      Inputs:
         this: the list to search through
         o: the Object to search for
      Results:
         return: true if the Object exists in the list.
                 false otherwise
      Ex.
      LList l = new LList()
      l.add(1)
      l.add(2)
      l.add(3)
      l.contains(1) -> true
      l.contains(2) -> true
      l.contains(3) -> true
      l.contains(4) -> false
      l.contains("3") -> false
      */
      LNode x = new LNode(o);
      LNode y = this.start;
      while(y != null){
         if(x.getData().equals(y.getData())) return true;
         y = y.getNext();
      }
      return false;
   }
   public int indexOf(Object o){
      /*
      Find the index of the given value in the list.
      Checks for equality by value, not by memory location,
      i.e. uses .equals() not == . If the same value is
      at multiple indices, return the smallest one. If the
      value is not in the list, return -1.
      Inputs:
         this: the list to search through
         o: the value to search for
      Results:
         return: the smallest index with a matching value
                 or -1 if the value is not in the list
      Ex.
      LList l = new LList()
      l.add(3)
      l.add(1)
      l.add(3)
      l.indexOf(3) -> 0
      l.indexOf(1) -> 1
      l.indexOf(2) -> -1
      */
      LNode x = new LNode(o);
      LNode y = this.start;
      int i = 0;
      while(y != null){
         if(x.getData().equals(y.getData())) return i;
         else{
            y = y.getNext();
            i++;
         }
      }
      return -1;
   }
}