package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;

public class GEOB extends Frame {

	private String mimeType;
	private String filename;
	private String contentDescription;
	private byte[] data;

	public GEOB(FrameHeader header) {
		super(header);
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getContentDescription() {
		return contentDescription;
	}

	public void setContentDescription(String contentDescription) {
		this.contentDescription = contentDescription;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Override
	void readBodyFromStream(ID3Reader reader) throws IOException,
			InvalidTagException {
		boolean unicode = reader.readBodyByte() != 0;
		mimeType = reader.readString(false);
		filename = reader.readString(unicode);
		contentDescription = reader.readString(unicode);
		data = reader.readRestOfFrame();
	}

	@Override
	public String toString() {
		return new StringBuilder().append(header.getId()).append(", \"").append(getFrameDescription()).append("\"").append(", Mime Type: ").append(mimeType).append(", filename: ")
				.append(filename).append(", content description: ").append(contentDescription).toString();
	}

	@Override
	public String getFrameDescription() {
		return "General encapsulated object";
	}
}
