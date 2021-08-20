package com.sipgate.mp3wav;


import org.junit.jupiter.api.Test;

import static com.sipgate.mp3wav.Converter.convertFrom;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;

public class ConverterTest {

	@Test
	public void testConvertToWav() throws Exception {

		try (
				final InputStream inputStream = getClass().getResourceAsStream("/test.mp3");
				final ByteArrayOutputStream output = new ByteArrayOutputStream();
		) {
			final AudioFormat audioFormat = new AudioFormat(44100, 8, 1, false, false);

			convertFrom(inputStream).withTargetFormat(audioFormat).to(output);

			final byte[] wavContent = output.toByteArray();

			final AudioFileFormat actualFileFormat = AudioSystem
					.getAudioFileFormat(new ByteArrayInputStream(wavContent));

			Files.write(Paths.get("/tmp/bla.wav"), wavContent);
			assertThat(actualFileFormat.getFormat().getChannels(), is(audioFormat.getChannels()));
			assertThat(actualFileFormat.getFormat().getSampleRate(), is(audioFormat.getSampleRate()));
			assertThat(actualFileFormat.getFormat().getEncoding(), is(audioFormat.getEncoding()));
			assertThat(actualFileFormat.getFormat().getFrameRate(), is(audioFormat.getFrameRate()));
			assertThat(actualFileFormat.getFormat().getFrameSize(), is(audioFormat.getFrameSize()));
			assertThat(actualFileFormat.getFormat().getSampleSizeInBits(), is(audioFormat.getSampleSizeInBits()));

			Files.write(Paths.get("/tmp/output.wav"), output.toByteArray());
		} finally {
			// intentionally left blank
		}
	}

	@Test
	public void testConvertToMp3() throws Exception {

		try (
				final InputStream inputStream = getClass().getResourceAsStream("/test.wav");
				final ByteArrayOutputStream output = new ByteArrayOutputStream();
		) {
			final AudioFormat audioFormat = new AudioFormat(44100, 8, 1, false, false);

			convertFrom(inputStream).withTargetFormat(audioFormat).to(output);

			final byte[] wavContent = output.toByteArray();

			final AudioFileFormat actualFileFormat = AudioSystem
					.getAudioFileFormat(new ByteArrayInputStream(wavContent));

			Files.write(Paths.get("/tmp/bla.wav"), wavContent);
			assertThat(actualFileFormat.getFormat().getChannels(), is(audioFormat.getChannels()));
			assertThat(actualFileFormat.getFormat().getSampleRate(), is(audioFormat.getSampleRate()));
			assertThat(actualFileFormat.getFormat().getEncoding(), is(audioFormat.getEncoding()));
			assertThat(actualFileFormat.getFormat().getFrameRate(), is(audioFormat.getFrameRate()));
			assertThat(actualFileFormat.getFormat().getFrameSize(), is(audioFormat.getFrameSize()));
			assertThat(actualFileFormat.getFormat().getSampleSizeInBits(), is(audioFormat.getSampleSizeInBits()));

//			Files.write(Paths.get("/tmp/output.wav"), output.toByteArray());
		} finally {
			// intentionally left blank
		}
	}
}
