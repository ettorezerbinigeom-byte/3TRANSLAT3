package it.ettore.traduttore;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Locale;

/** Un'unica schermata che mostra il traduttore (assets/index.html). */
public class MainActivity extends Activity {

    private WebView web;
    private TextToSpeech voce;
    private boolean vocePronta = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        voce = new TextToSpeech(this, stato -> vocePronta = (stato == TextToSpeech.SUCCESS));

        web = new WebView(this);
        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        web.setWebViewClient(new WebViewClient());
        web.addJavascriptInterface(new Ponte(), "Android");
        setContentView(web);

        if (savedInstanceState != null) {
            web.restoreState(savedInstanceState);
        } else {
            web.loadUrl("file:///android_asset/index.html");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        web.saveState(outState);
    }

    @Override
    protected void onDestroy() {
        if (voce != null) voce.shutdown();
        super.onDestroy();
    }

    /** Funzioni del telefono richiamabili dalla pagina (voce e appunti). */
    private class Ponte {
        @JavascriptInterface
        public void parla(String testo, String lingua) {
            if (!vocePronta) return;
            String tag = lingua.equals("iw") ? "he" : lingua;
            voce.setLanguage(Locale.forLanguageTag(tag));
            voce.speak(testo, TextToSpeech.QUEUE_FLUSH, null, "traduttore");
        }

        @JavascriptInterface
        public void copia(String testo) {
            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            cm.setPrimaryClip(ClipData.newPlainText("traduzione", testo));
        }
    }
}
