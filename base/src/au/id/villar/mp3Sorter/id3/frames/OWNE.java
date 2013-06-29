package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;
import java.math.BigDecimal;

public class OWNE extends Frame {

	private String currency;
	private BigDecimal price;
	private String date;
	private String seller;

	public OWNE(FrameHeader header) {
		super(header);
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	@Override
	void readBodyFromStream(ID3Reader reader) throws IOException,
			InvalidTagException {
		boolean unicode = reader.readBodyByte() != 0;
		StringBuilder builder = new StringBuilder(3);
		for(int x = 0; x < 3; x++)
			builder.append((char)reader.readBodyByte());
		currency = builder.toString();
		price = new BigDecimal(reader.readString(false));
		builder = new StringBuilder(10);
		for(int x = 0; x < 8; x++)
			builder.append((char)reader.readBodyByte());
		builder.insert(4, '-').insert(7, '-');
		date = builder.toString();
		seller = reader.readString(unicode);
	}

	@Override
	public String toString() {
		return new StringBuilder().append(header.getId()).append(", \"").append(getFrameDescription()).append("\"").append(", price: ").append(currency).append(' ').append(price)
				.append(", date: ").append(date).append(", seller: ").append(seller).toString();
	}

	@Override
	public String getFrameDescription() {
		return "Ownership frame";
	}
}
