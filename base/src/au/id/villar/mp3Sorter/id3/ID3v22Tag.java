package au.id.villar.mp3Sorter.id3;

import au.id.villar.mp3Sorter.id3.frames.Frame;
import au.id.villar.mp3Sorter.id3.frames.FrameHeader;
import au.id.villar.mp3Sorter.id3.frames.V2FrameHeader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ID3v22Tag extends ID3v2Tag {

	private boolean compressed;
	private List<Frame> frames;

	public ID3v22Tag(byte flags) {
		super(2);
		frames = new ArrayList<>();
		compressed = (flags & 0x40) != 0;
	}

	public boolean isCompressed() {
		return compressed;
	}

	public void setCompressed(boolean compressed) {
		this.compressed = compressed;
	}

	public List<Frame> getFrames() {
		return frames;
	}

	public void setFrames(List<Frame> frames) {
		this.frames = frames;
	}

	@Override
	void completeFromStream(ID3Reader reader) throws IOException, InvalidTagException {
		while(reader.getCounter() > 0) {
			FrameHeader frameHeader = new V2FrameHeader();
			Frame frame = frameHeader.readFromStream(reader);
			if(frame != null) {
				frames.add(frame);
			} else {
				break;
			}
		}
		while(reader.getCounter() > 0)
			reader.readBodyByte();
	}
}
