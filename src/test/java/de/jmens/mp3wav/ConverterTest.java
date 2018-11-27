package de.jmens.mp3wav;

import static com.sipgate.mp3wav.mp3wav.Converter.convertFrom;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import javax.sound.sampled.AudioFormat;
import org.junit.Test;

public class ConverterTest {

	@Test
	public void testConverer() throws Exception {

		try (
				final InputStream inputStream = getClass().getResourceAsStream("/test.mp3");
				final ByteArrayOutputStream output = new ByteArrayOutputStream();
		) {
			final AudioFormat audioFormat = new AudioFormat(44100, 8, 1, true, false);

			convertFrom(inputStream).withFormat(audioFormat).to(output);

//			Files.write(Paths.get("/tmp/output.wav"), output.toByteArray());
		} finally {
			// intentionally left blank
		}
	}
}
