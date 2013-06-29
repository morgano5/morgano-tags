package au.id.villar.mp3Sorter.id3.frames;

import au.id.villar.mp3Sorter.id3.ID3Reader;
import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class EQUA extends Frame {

	private List<EqualisationBand> bands;

	public EQUA(FrameHeader header) {
		super(header);
	}

	@Override
	void readBodyFromStream(ID3Reader reader) throws IOException,
			InvalidTagException {
		int adjustmentBytes;
		int band;
		BigInteger adjustment;
		boolean minus;

		adjustmentBytes = reader.readBodyByte();
		adjustmentBytes = adjustmentBytes / 8 + ((adjustmentBytes % 8 != 0)? 1: 0);
		bands = new ArrayList<>();
		while(reader.getFrameCounter() > 0) {
			band = reader.read2Bytes();
			minus = ((band & 0x8000) == 0);
			band &= 0x7FFF;
			adjustment = reader.readBigInteger(adjustmentBytes, minus);
			bands.add(new EqualisationBand(band, adjustment));
		}
	}

	public List<EqualisationBand> getBands() {
		return bands;
	}

	public void setBands(List<EqualisationBand> bands) {
		this.bands = bands;
	}

	@Override
	public String toString() {
		return new StringBuilder().append(header.getId()).append(", \"").append(getFrameDescription()).append("\"").append(", Bands: ").append(bands).toString();
	}

	public static class EqualisationBand {
		int band;
		BigInteger adjustment;

		public EqualisationBand(int band, BigInteger adjustment) {
			this.band = band;
			this.adjustment = adjustment;
		}

		public int getBand() { return band; }
		public BigInteger getAdjustment() { return adjustment; }

		@Override
		public String toString() {
			return new StringBuilder().append(band).append(" Hz: ").append(" ").append(adjustment).toString();
		}
	}

	@Override
	public String getFrameDescription() {
		return "Equalization";
	}
}
