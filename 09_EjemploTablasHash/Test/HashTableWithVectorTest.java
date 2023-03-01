import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class HashTableWithVectorTest {

	@Test
	void Inserttest() {
		HashTableWithVector<Integer> myHashTable;
		myHashTable = new HashTableWithVector<Integer>(
				new NumbersHashFunction(5)
				, 5
				); 
		
		myHashTable.insert(10);
		myHashTable.insert(22);
		myHashTable.insert(5);
		myHashTable.insert(30);
		myHashTable.insert(33);
		
		ArrayList<Integer> result = myHashTable.get(0);
		
		assertEquals(result.get(0), 10);
		assertEquals(result.get(1), 5);
		assertEquals(result.get(2), 30);
		
	}

}
