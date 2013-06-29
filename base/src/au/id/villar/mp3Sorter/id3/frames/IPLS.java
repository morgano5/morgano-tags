package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IPLS extends Frame {

	private List<String> involvedPeople;

	public IPLS(FrameHeader header) {
		super(header);
	}

	public List<String> getInvolvedPeople() {
		return involvedPeople;
	}

	public void setInvolvedPeople(List<String> involvedPeople) {
		this.involvedPeople = involvedPeople;
	}

	@Override
	void readBodyFromStream(ID3Reader reader) throws IOException, InvalidTagException {
		boolean unicode;

		unicode = reader.readBodyByte() == 1;
		involvedPeople = new ArrayList<>();
		while(reader.getFrameCounter() > 0)
			involvedPeople.add(reader.readString(unicode));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append(header.getId()).append(", \"").append(getFrameDescription()).append("\"").append(", involved people: [");
		for(String person: involvedPeople) {
			builder.append(" \"").append(person).append('"');
		}
		builder.append(" ]");
		return builder.toString();
	}

	@Override
	public String getFrameDescription() {
		return "Involved people list";
	}
}
