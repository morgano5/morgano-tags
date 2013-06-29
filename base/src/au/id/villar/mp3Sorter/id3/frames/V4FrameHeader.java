package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;

public class V4FrameHeader extends FrameHeader {

	@Override
	public Frame readFromStream(ID3Reader reader) throws IOException, InvalidTagException {
		// TODO Implement method body
		throw new RuntimeException("not yet implemented");
	}
}
