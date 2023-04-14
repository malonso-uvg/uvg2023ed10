import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import structure5.SplayTree;

class SplayTreeTests {

	@Test
	void test() {
		SplayTree<String> mySplayTree = new SplayTree<String>();
		
		mySplayTree.add("1");
		mySplayTree.add("2");
		mySplayTree.add("3");
		mySplayTree.add("4");

		mySplayTree.get("2");
		mySplayTree.get("2");
		
	}

}
