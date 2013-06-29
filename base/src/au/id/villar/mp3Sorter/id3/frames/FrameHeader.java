package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;

public abstract class FrameHeader {

	protected String id;

	public String getId() {
		return id;
	}

	public abstract Frame readFromStream(ID3Reader reader) throws IOException, InvalidTagException;

}
