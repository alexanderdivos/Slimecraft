package me.mrCookieSlime.Slimecraft.Setup;

import javax.swing.ToolTipManager;

import me.mrCookieSlime.Slimecraft.Messages.ButtonText;
import me.mrCookieSlime.Slimecraft.Messages.Error;
import me.mrCookieSlime.Slimecraft.Messages.Tooltip;
import me.mrCookieSlime.Slimecraft.Options.Options;
import me.mrCookieSlime.Slimecraft.Options.VersionManager;

public class Localization {
	
	public static void setup() {
		
		ToolTipManager.sharedInstance().setInitialDelay(0);
		
		if (System.getProperties().getProperty("user.language").equalsIgnoreCase("de")) {
			ButtonText.PLUGINS = "Plugins für Bukkit";
			ButtonText.MODS = "Mods für Minecraft";
			ButtonText.LOGIN = "Einloggen";
			ButtonText.RESSOURCE_PACKS = "Resource Packs";
			ButtonText.UPDATE_OUT = "Es ist ein neues Minecraft Update verfügbar: ";
			ButtonText.SNAPSHOT_OUT = "Es ist ein neuer Snapshot verfügbar: ";
			ButtonText.VERSION_MANAGER = "Versions-Manager";
			ButtonText.CREATE_NEW = "Neue Erstellen";
			ButtonText.NAME_DESCRIPTION = "Name der neuen Minecraft Version";
			ButtonText.CHANGELOG = "Minecraft Update News";
			ButtonText.MOD_ADD_DESCRIPTION = "Deaktivierte Mods";
			ButtonText.MOD_REMOVE_DESCRIPTION = "Aktivierte Mods";
			ButtonText.MODPACK_DESCRIPTION = "Liste aller Modpacks";
			ButtonText.MODPACK_NAME = "Bitte gib einen Namen ein";
			ButtonText.CREDITS = "Credits";
			Tooltip.SERVER_STATUS_ON = "Der Minecraft Login Server ist online!";
			Tooltip.SERVER_STATUS_OFF = "Der Minecraft Login Server ist offline! Du kannst dich derzeit nicht einloggen";
			Tooltip.TWITTER = "Folge mir auf Twitter";
			Tooltip.STEAM = "Zock mit mir auf Steam";
			Tooltip.YOUTUBE = "Abonniert mich auf YouTube";
			Error.UPDATE = "Es ist ein neues Update für Slimecraft verfügbar! Es wird nun installiert...";
			Error.BAD_LOGIN = "Falsch Eingeloggt! Überprüfe deinen Usernamen und dein Passwort";
			Error.BAD_REQUEST = "Falsche Amfrage! Dies könnte ein Slimecraft Bug sein :/";
			Error.DOWNLOADING = "Lade Dateien herunter...  %percentage%";
			Error.INSTALLED = "Version %version% wurde installiert!";
			Error.MIGRATED = "Dieser Account wurde migriert! Benutze stattdessen deine E-Mail-Adresse!";
			Error.NOT_PREMIUM = "Dieser Account ist nicht Premium! Kaufe Minecraft zuerst";
			Error.TOO_MANY_FAILS = "Zu Viele fehlgeschlagene Logins! Versuche es später erneut!";
			Error.MOVING = "Kopiere Dateien...  %percentage%";
			Error.WRONG_OS = "Dein Betriebssystem unterstützt Slimecraft leider nicht! Bitte lade die entsprechende Slimecraft Version herunter.";
			Error.MODPACK_CREATED = "Die momentanen Einstellungen wurden nun als Modpack gespeichert";
			Error.MODPACK_FAILED = "Bitte gib einen Modpack Namen ein";
			Error.MODPACK_DELETED = "Modpack erfolgreich gelöscht";
			Error.MODPACK_LOADED = "Modpack erfolgreich geladen";
			Options.OPTIONS = "Slimecraft Einstellungen";
			Options.jarpath = "                          Benutze Version";
		    Options.minecraftpath = "                  Pfad des Minecraft Ordners";
		    Options.width1 = "       Weite:";
		    Options.height1 = "       Höhe:";
		    Options.console_name = "Zeige Entwicklungskonsole";
		    Options.fullscreen_name = "Starte Minecraft im Vollbild-Modus";
		    Options.save_data_name = "Speichere Username und Passwort";
		    VersionManager.not_installed = " Nicht Installierte Versionen";
		    VersionManager.install_name = "Installieren";
		}
		else {
			ButtonText.PLUGINS = "Get Bukkit Plugins";
			ButtonText.MODS = "Get Minecraft Mods";
			ButtonText.LOGIN = "Login";
			ButtonText.RESSOURCE_PACKS = "Get Resource Packs";
			ButtonText.UPDATE_OUT = "A new Minecraft Update is available for Download: ";
			ButtonText.SNAPSHOT_OUT = "A new Snapshot is available for Download: ";
			ButtonText.VERSION_MANAGER = "Version-Manager";
			ButtonText.CREATE_NEW = "Create New";
			ButtonText.NAME_DESCRIPTION = "Name of the new Instance";
			ButtonText.CHANGELOG = "Minecraft Update News";
			ButtonText.MOD_ADD_DESCRIPTION = "Disabled mods";
			ButtonText.MOD_REMOVE_DESCRIPTION = "Enabled mods";
			ButtonText.MODPACK_DESCRIPTION = "Modpack List";
			ButtonText.MODPACK_NAME = "Enter a Modpack Name";
			ButtonText.CREDITS = "Mitwirkende";
			Tooltip.SERVER_STATUS_ON = "The Minecraft Login Server is online!";
			Tooltip.SERVER_STATUS_OFF = "The Minecraft Login Server is offline! You may not login at the moment!";
			Tooltip.TWITTER = "Follow me on Twitter";
			Tooltip.STEAM = "Game with me on Steam";
			Tooltip.YOUTUBE = "Subscribe to me on YouTube";
			Error.UPDATE = "Slimecraft has found an Update! It'll be downloaded now...";
			Error.BAD_LOGIN = "Bad Login! Check your Username and Password";
			Error.BAD_REQUEST = "Bad Request! This may be a Slimecraft Bug :/";
			Error.DOWNLOADING = "Downloading Files...  %percentage%";
			Error.INSTALLED = "Successfully installed Version %version%";
			Error.MIGRATED = "Account migrated! Please use your E-Mail-Adress!";
			Error.NOT_PREMIUM = "Not Premium! Please buy Minecraft first!";
			Error.TOO_MANY_FAILS = "Too many failed Logins!";
			Error.MOVING = "Copying Files...  %percentage%";
			Error.WRONG_OS = "I'm sorry but your Operating System does not support Slimecraft! Please download the appropriate Slimecraft Version!";
			Error.MODPACK_CREATED = "Successfully saved current Setup as a Modpack";
			Error.MODPACK_FAILED = "Please enter a valid Name";
			Error.MODPACK_DELETED = "Successfully deleted this Modpack";
			Error.MODPACK_LOADED = "Successfully loaded this Modpack";
			Options.OPTIONS = "Slimecraft Options";
			Options.jarpath = "                          Use Version";
			Options.minecraftpath = "                          Path of .minecraft";
			Options.width1 = "        width:";
			Options.height1 = "       height:";
			Options.console_name = "Show developement console";
			Options.fullscreen_name = "Start minecraft in fullscreen mode";
			Options.save_data_name = "Remember Login-Data";
			VersionManager.not_installed = " Not Installed Versions";
			VersionManager.install_name = "Install";
		}
	}

}
