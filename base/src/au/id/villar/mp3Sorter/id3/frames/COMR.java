package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;

public class COMR extends Frame {

	private String[] prices;
	private String validUntil;
	private String contact;
	private ReceivedAs receivedAs;
	private String seller;
	private String description;
	private String mimeType;
	private byte[] sellerLogo;

	public static enum ReceivedAs {
		OTHER, CD, COMPRESSED_CD, INTERNET_FILE, INTERNET_STREAM, NOTE_SHEETS, BOOK_NOTE_SHEETS, MUSIC_OTHER, NON_MUSIC;
		public static ReceivedAs getReceivedAs(int type) {
			switch(type) {
				case 0: return OTHER;
				case 1: return CD;
				case 2: return COMPRESSED_CD;
				case 3: return INTERNET_FILE;
				case 4: return INTERNET_STREAM;
				case 5: return NOTE_SHEETS;
				case 6: return BOOK_NOTE_SHEETS;
				case 7: return MUSIC_OTHER;
				case 8: return NON_MUSIC;
				default: throw new IllegalArgumentException("Unknown value for 'ReceivedAs': " + type);
			}
		}
	}

	public COMR(FrameHeader header) {
		super(header);
	}

	public String[] getPrices() {
		return prices;
	}

	public void setPrices(String[] prices) {
		this.prices = prices;
	}

	public String getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(String validUntil) {
		this.validUntil = validUntil;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public ReceivedAs getReceivedAs() {
		return receivedAs;
	}

	public void setReceivedAs(ReceivedAs receivedAs) {
		this.receivedAs = receivedAs;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public byte[] getSellerLogo() {
		return sellerLogo;
	}

	public void setSellerLogo(byte[] sellerLogo) {
		this.sellerLogo = sellerLogo;
	}

	@Override
	void readBodyFromStream(ID3Reader reader) throws IOException,
			InvalidTagException {
		boolean unicode = reader.readBodyByte() != 0;
		prices = reader.readString(false).split("/");
		StringBuilder buffer = new StringBuilder(10);
		for(int x = 0; x < 8; x++) {
			buffer.append((char)reader.readBodyByte());
		}
		validUntil = buffer.insert(4, '-').insert(7, '-').toString();
		contact = reader.readString(false);
		receivedAs = ReceivedAs.getReceivedAs(reader.readBodyByte());
		seller = reader.readString(unicode);
		description = reader.readString(unicode);
		if(reader.getFrameCounter() > 0) {
			mimeType = reader.readString(false);
			sellerLogo = reader.readRestOfFrame();
		}
	}

	@Override
	public String toString() {
		return new StringBuilder().append(header.getId()).append(", \"").append(getFrameDescription()).append("\"").append(", prices: ").append(prices).append(", valid until: ")
				.append(validUntil).append(", contact: ").append(contact).append(", received as: ")
				.append(receivedAs).append(", seller: ").append(seller).append(", description: ")
				.append(description).toString();
	}

	@Override
	public String getFrameDescription() {
		return "Commercial frame";
	}
}
