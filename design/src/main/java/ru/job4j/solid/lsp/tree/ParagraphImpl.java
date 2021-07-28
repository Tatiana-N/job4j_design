package ru.job4j.solid.lsp.tree;

import lombok.Getter;
import ru.job4j.solid.lsp.tree.api.Paragraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
public class ParagraphImpl implements Paragraph {
	String name;
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ParagraphImpl)) {
			return false;
		}
		ParagraphImpl paragraph = (ParagraphImpl) o;
		return getName().equals(paragraph.getName()) && getParagraphList().equals(paragraph.getParagraphList());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getName(), getParagraphList());
	}
	
	List<Paragraph> paragraphList = new ArrayList<>();
	
	public ParagraphImpl(String name) {
		this.name = name;
	}
	
	@Override
	public List<Paragraph> getChildren() {
		return paragraphList;
	}
	
	@Override
	public void putChildren(Paragraph... paragraph) {
		paragraphList.addAll(Arrays.asList(paragraph));
	}
}
