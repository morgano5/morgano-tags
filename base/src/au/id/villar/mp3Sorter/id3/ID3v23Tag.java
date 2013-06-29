package au.id.villar.mp3Sorter.id3;

import au.id.villar.mp3Sorter.id3.frames.Frame;
import au.id.villar.mp3Sorter.id3.frames.FrameHeader;
import au.id.villar.mp3Sorter.id3.frames.V3FrameHeader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ID3v23Tag extends ID3v2Tag {

	private boolean extendedHeader;
	private boolean experimental;
	private int extHeaderSize;
	private boolean hasCRC;
	private int padding;
	private int crc32;
	private List<Frame> frames;

	public ID3v23Tag(byte flags) {
		super(3);
		frames = new ArrayList<>();
		extendedHeader = (flags & 0x40) != 0;
		experimental = (flags & 0x20) != 0;
	}

	public boolean isExtendedHeader() {
		return extendedHeader;
	}

	public void setExtendedHeader(boolean extendedHeader) {
		this.extendedHeader = extendedHeader;
	}

	public boolean isExperimental() {
		return experimental;
	}

	public void setExperimental(boolean experimental) {
		this.experimental = experimental;
	}

	public int getExtHeaderSize() {
		return extHeaderSize;
	}

	public void setExtHeaderSize(int extHeaderSize) {
		this.extHeaderSize = extHeaderSize;
	}

	public boolean isHasCRC() {
		return hasCRC;
	}

	public void setHasCRC(boolean hasCRC) {
		this.hasCRC = hasCRC;
	}

	public int getPadding() {
		return padding;
	}

	public void setPadding(int padding) {
		this.padding = padding;
	}

	public int getCrc32() {
		return crc32;
	}

	public void setCrc32(int crc32) {
		this.crc32 = crc32;
	}

	public List<Frame> getFrames() {
		return frames;
	}

	public void setFrames(List<Frame> frames) {
		this.frames = frames;
	}

	@Override
	void completeFromStream(ID3Reader reader) throws IOException, InvalidTagException {
		int readByte;

		if(extendedHeader) {
			extHeaderSize = (reader.readBodyByte() << 24) | (reader.readBodyByte() << 16)
					| (reader.readBodyByte() << 8) | reader.readBodyByte();
			readByte = reader.readBodyByte();
			hasCRC = readByte != 0;
			padding = (reader.readBodyByte() << 24) | (reader.readBodyByte() << 16)
					| (reader.readBodyByte() << 8) | reader.readBodyByte();
			if(hasCRC) {
				crc32 = (reader.readBodyByte() << 24) | (reader.readBodyByte() << 16)
						| (reader.readBodyByte() << 8) | reader.readBodyByte();
			}
		}
		while(reader.getCounter() > 0) {
			FrameHeader frameHeader = new V3FrameHeader();
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
