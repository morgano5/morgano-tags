package au.id.villar.mp3Sorter;

import au.id.villar.mp3Sorter.id3.InvalidTagException;

import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * Date: 29/10/11
 * Time: 12:32 PM
 */
public interface FileComponentParser<T> {

	T tryParseComponent(BufferedInputStream stream) throws IOException, InvalidTagException;

}
