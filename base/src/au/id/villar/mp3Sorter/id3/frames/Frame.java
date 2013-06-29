package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;

public abstract class Frame {

	protected FrameHeader header;

	Frame(FrameHeader header) {
		if(header == null) {
			throw new NullPointerException("parameter header cannot be null");
		}
		this.header = header;
	}

	abstract void readBodyFromStream(ID3Reader reader) throws IOException, InvalidTagException;

	abstract String getFrameDescription();

}
