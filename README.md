# Rupture Reborn — Forge 1.21.11

Ein neu aufgebautes, clientseitiges Forge-Projekt mit einem einfachen Mod-Menü (Rechts-Shift). Die bereitgestellte Rupture-JAR ist ein sehr altes, gebündeltes Minecraft-1.8.x-Clientpaket; sie lässt sich nicht direkt auf 1.21.11 portieren. Die JSON-Datei ist ein Launcher-Profil für Minecraft 1.8 und enthält keine Mod-Konfiguration.

## Enthalten
- Menü mit Rechts-Shift
- Koordinaten-HUD
- FPS-Anzeige
- Sprint Assist
- Fullbright-Schalter als UI-Platzhalter (noch ohne Gamma-Anpassung)

Reach-/Autoclicker- und sonstige automatisierte Kampfmodule sind nicht enthalten. Ebenso sind die alten Module aus dem JAR nicht pauschal übernommen: viele davon benötigen tiefgreifende, versionsspezifische Änderungen.

## Kompilieren auf GitHub / lokal
1. Installiere **JDK 21**.
2. Lege den Inhalt dieses Ordners in ein neues GitHub-Repository.
3. Öffne das Repository lokal (z. B. mit IntelliJ IDEA) oder nutze GitHub Actions.
4. Starte im Projektordner:
   - Windows: `gradlew.bat build` (nachdem du den offiziellen Gradle-Wrapper ergänzt hast)
   - Linux/macOS: `./gradlew build`
5. Die fertige Mod-JAR liegt anschließend unter `build/libs/`.

Dieses Quellprojekt enthält absichtlich keinen Gradle-Wrapper. In IntelliJ kann Gradle über die installierte Gradle-Version gestartet werden; alternativ den Wrapper über `gradle wrapper` erzeugen.

## GitHub Actions
Ein einfacher Workflow kann `gradle build` mit Java 21 ausführen. Die Forge-Gradle-Abhängigkeiten werden beim ersten Lauf heruntergeladen.

### Direkt über GitHub Actions bauen
Nach dem Push startet der Workflow **Build Forge mod** automatisch. Unter *Actions* → letzter Lauf → *Artifacts* kannst du `rupture-reborn` herunterladen. Voraussetzung ist ein erfolgreich abgeschlossener Build.
