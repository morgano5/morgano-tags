package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;

public class RBUF extends Frame {

	int bufferSize;
	boolean embedded;
	long offsetToNextTag = -1;

	public RBUF(FrameHeader header) {
		super(header);
	}

	public int getBufferSize() {
		return bufferSize;
	}

	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	public boolean isEmbedded() {
		return embedded;
	}

	public void setEmbedded(boolean embedded) {
		this.embedded = embedded;
	}

	public long getOffsetToNextTag() {
		return offsetToNextTag;
	}

	public void setOffsetToNextTag(long offsetToNextTag) {
		this.offsetToNextTag = offsetToNextTag;
	}

	@Override
	void readBodyFromStream(ID3Reader reader) throws IOException,
			InvalidTagException {
		bufferSize = reader.read3Bytes();
		embedded = ((reader.readBodyByte() & 1) == 1);
		if(reader.getFrameCounter() > 0) {
			offsetToNextTag = (reader.readInt() & 0xFFFFFFFFL);
		}
	}

	@Override
	public String toString() {
		return new StringBuilder().append(header.getId()).append(", \"").append(getFrameDescription()).append("\"").append(", bufferSize: ").append(bufferSize).append(", embedded:")
				.append(embedded).append(", offsetToNextTag: ").append(offsetToNextTag).toString();
	}

	@Override
	public String getFrameDescription() {
		return "Recommended buffer size";
	}
}
