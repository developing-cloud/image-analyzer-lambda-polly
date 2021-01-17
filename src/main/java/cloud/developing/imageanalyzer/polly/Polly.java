package cloud.developing.imageanalyzer.polly;

import static java.lang.System.currentTimeMillis;

import java.util.Date;

import com.amazonaws.services.polly.AmazonPolly;
import com.amazonaws.services.polly.AmazonPollyClientBuilder;
import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.VoiceId;
import com.amazonaws.services.polly.presign.SynthesizeSpeechPresignRequest;

public class Polly {

	private final static String messagePrefix = "Hej! System analizy obrazu właśnie zidentifikował następujące obiekty na zdjęciu bądź filmie: ";

	private final AmazonPolly polly = AmazonPollyClientBuilder.defaultClient();

	public String speak(String text) throws Exception {
		var url = polly.presigners()
				.getPresignedSynthesizeSpeechUrl(new SynthesizeSpeechPresignRequest().withLanguageCode("pl-PL")
						.withVoiceId(VoiceId.Ewa).withOutputFormat(OutputFormat.Mp3).withText(messagePrefix + text)
						.withExpirationDate(new Date(currentTimeMillis() + 1000 * 60 * 60)));
		return url.toString();
	}

}
