# 3TRANSLAT3 – App Android (APK)

App autonoma per Android: si installa sul telefono e funziona da sola, **senza PC e senza server**.
Serve solo la connessione internet (le traduzioni le fa Google Traduttore).

Stesse funzioni della versione web: 3 caselle con lingua a scelta (circa 250 lingue +
siciliano, veneziano, ligure, lombardo, friulano), traduzione automatica mentre scrivi,
pulsanti Ascolta / Copia / Cancella.

## Come si ottiene l'APK

Per trasformare il progetto in un file `.apk` servono gli strumenti ufficiali Android.
Invece di installarli sul tuo PC, li usa **GitHub gratuitamente** al posto tuo: carichi la
cartella e dopo circa 5 minuti trovi l'APK pronto da scaricare.

### Strada A – lo faccio io (consigliata)
1. Crea un account gratuito su github.com (se non l'hai già).
2. Collega GitHub a Claude: https://claude.ai/connect-github
3. Su GitHub crea un repository **vuoto** (pulsante **New**), nome per esempio `traduttore3`.
4. Scrivimi il nome (es. `tuonome/traduttore3`): carico tutto, controllo che la costruzione
   vada a buon fine e ti mando il link diretto all'APK.

### Strada B – da solo
1. Su github.com crea un repository nuovo (pulsante **New**, nome `traduttore3`,
   spunta *Add a README file*).
2. Nel repository: **Add file → Upload files**. Scompatta `traduttore-apk.zip` sul PC e
   trascina **il contenuto** della cartella (`app`, `build.gradle`, `settings.gradle`,
   `gradle.properties`...). Premi **Commit changes**.
3. La cartella nascosta `.github` di solito non si carica trascinando, quindi creala a mano:
   **Add file → Create new file**, come nome scrivi `.github/workflows/build-apk.yml`
   e incolla dentro il contenuto del file `build-apk.yml` (lo trovi anche in questa cartella).
   Premi **Commit changes**.
4. Apri la scheda **Actions**: parte "Costruisci APK". Dopo circa 5 minuti diventa verde ✅.
5. Torna nella pagina principale del repository, a destra clicca **Releases** e scarica
   `3TRANSLAT3.apk`. Puoi farlo direttamente dal telefono.

## Installare l'APK sul telefono
1. Apri il file `3TRANSLAT3.apk` scaricato (dalle notifiche o da *File → Download*).
2. Android chiede di consentire l'installazione da "origini sconosciute" per il browser o
   per l'app File: **consenti**. È normale per le app che non arrivano dal Play Store.
3. Se Play Protect avvisa che l'app non è conosciuta: **Installa comunque**.
   L'app usa solo il permesso Internet.
4. Trovi l'icona **3TRANSLAT3** tra le app.

## Aggiornamenti
Se modifichi qualcosa (per esempio `app/src/main/assets/index.html`) e lo ricarichi su
GitHub, viene creato in automatico un nuovo APK nelle Releases. Si installa sopra a quello
vecchio senza disinstallarlo, perché la chiave di firma è sempre la stessa
(`app/traduttore.keystore`).

## Note
- `traduttore.html` è la stessa app in un unico file: si può aprire anche nel browser di un PC.
- La voce di "Ascolta" è quella del telefono: per i dialetti di solito non c'è una voce adatta.
- iPhone: gli APK funzionano solo su Android. Su iPhone si usa la versione web.
