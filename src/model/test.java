package model;

import java.util.TreeSet;

public class test {
	public static void main(String[] args){
		TreeSet<String> dictionaryTree = new TreeSet<String>();
		dictionaryTree.add("apple");
		dictionaryTree.add("banana");
		dictionaryTree.add("car");
		dictionaryTree.add("dark");
		dictionaryTree.add("ear");
		dictionaryTree.add("far");
		dictionaryTree.add("goose");
		dictionaryTree.add("hint");
		dictionaryTree.add("ice");
		dictionaryTree.add("jack");
		dictionaryTree.add("knife");
		dictionaryTree.add("light");
		dictionaryTree.add("mother");
		dictionaryTree.add("need");
		dictionaryTree.add("old");
		dictionaryTree.add("paper");
		dictionaryTree.add("queen");
		dictionaryTree.add("rabit");
		dictionaryTree.add("snack");
		dictionaryTree.add("tee");
		dictionaryTree.add("unique");
		dictionaryTree.add("violin");
		dictionaryTree.add("war");
		dictionaryTree.add("x-ray");
		dictionaryTree.add("yellow");
		dictionaryTree.add("zoo");
		TxtFileParser.getInstance().readFromTxtFile("file/test.txt");
		TreeSet<String> tree = Dictionary.getInstance().getDictionaryTree();
		String a = tree.toString()+"f";
		String b = dictionaryTree.toString()+"f";
		System.out.println(tree.toString());
		System.out.println(dictionaryTree.toString());
		System.out.println(a.equals(b));
	}
}
