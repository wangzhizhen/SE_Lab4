package model;

public interface IDictionaryParser {
	public  void init(String file);
	public  IDictionary getDictionary();
	public  void setDictionary(IDictionary dictionary);
}
