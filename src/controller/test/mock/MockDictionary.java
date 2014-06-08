package controller.test.mock;

public class MockDictionary implements IDictionary{

	MockDictionary mockDictionary = new MockDictionary();
	@Override
	public IDictionary getInstance() {
		// TODO Auto-generated method stub
		return mockDictionary;
	}

	@Override
	public int getWordListLenthAt(int letterPosition) {
		// TODO Auto-generated method stub
		return 0;
	}
	

	
}
