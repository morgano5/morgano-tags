package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;

public class AENC extends Frame {

	private String ownerId;
	private int previewStart;
	private int previewLength;
	private byte[] encryptionInfo;

	public AENC(FrameHeader header) {
		super(header);
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public int getPreviewStart() {
		return previewStart;
	}

	public void setPreviewStart(int previewStart) {
		this.previewStart = previewStart;
	}

	public int getPreviewLength() {
		return previewLength;
	}

	public void setPreviewLength(int previewLength) {
		this.previewLength = previewLength;
	}

	public byte[] getEncryptionInfo() {
		return encryptionInfo;
	}

	public void setEncryptionInfo(byte[] encryptionInfo) {
		this.encryptionInfo = encryptionInfo;
	}

	@Override
	void readBodyFromStream(ID3Reader reader) throws IOException,
			InvalidTagException {
		ownerId = reader.readString(false);
		previewStart = reader.read2Bytes();
		previewLength = reader.read2Bytes();
		encryptionInfo = reader.readRestOfFrame();
	}

	@Override
	public String toString() {
		return new StringBuilder().append(header.getId()).append(", \"").append(getFrameDescription()).append("\"").append(", owner ID: ").append(ownerId).append(", preview start: ")
				.append(previewStart).append(", preview length: ").append(previewLength).toString();
	}

	@Override
	public String getFrameDescription() {
		return "Audio encryption";
	}
}
