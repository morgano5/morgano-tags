package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;

public class LINK extends Frame {

	private int frameId;
	private String url;
	private String additionalData;

	public LINK(FrameHeader header) {
		super(header);
	}

	public int getFrameId() {
		return frameId;
	}

	public void setFrameId(int frameId) {
		this.frameId = frameId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAdditionalData() {
		return additionalData;
	}

	public void setAdditionalData(String additionalData) {
		this.additionalData = additionalData;
	}

	@Override
	void readBodyFromStream(ID3Reader reader) throws IOException,
			InvalidTagException {
		frameId = reader.read3Bytes();
		url = reader.readString(false);
		additionalData = reader.readString(false);
	}

	@Override
	public String toString() {
		return new StringBuilder().append(header.getId()).append(", \"").append(getFrameDescription()).append("\"").append(", frameId: ").append(frameId).append(", url: ")
				.append(url).append(", additional data: ").append(additionalData).toString();
	}

	@Override
	public String getFrameDescription() {
		return "Linked information";
	}
}
