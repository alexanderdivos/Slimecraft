package me.mrCookieSlime.Slimecraft.Downloader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
	
	private static List<String> files = new ArrayList<String>();
	private static List<String> urls = new ArrayList<String>();
	
	public static void registerDownloads() {
		
		// DownloadJob: Images
		
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\icon.png").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\icon.png");
			urls.add("https://dl.dropboxusercontent.com/s/j3o4rkj7nwya4mo/icon.png?token_hash=AAFiJ1gkKlDSQwSoVWIqYe_xSQbyj40BrBfHRPKBIxNnJQ&dl=1");
		}
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\Attention.png").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\Attention.png");
			urls.add("https://dl.dropboxusercontent.com/s/olgrn8bimwmtf1w/Attention.png?dl=1&token_hash=AAHRy2M44Kyic_rSIuh7Gvd1_GRp7T5jrKmXqzhHMc2lLw");
		}
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\twitter.png").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\twitter.png");
			urls.add("https://dl.dropboxusercontent.com/s/hp2d1rpolszgzed/twitter.png?dl=1&token_hash=AAH2JJgOPAFnGkNupjYFlCZr8XGC19_HWA5pduJk25X40g");
		}
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\steam.png").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\steam.png");
			urls.add("https://dl.dropboxusercontent.com/s/0s9a8cawvttuef6/steam.png?dl=1&token_hash=AAHX33wB6_jrNjpmUw7PpCX1KYtZ74p3LSQSpgwuaGU_8w");
		}
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\youtube.png").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\youtube.png");
			urls.add("https://dl.dropboxusercontent.com/s/i8fw7eiieif68e7/youtube.png?dl=1&token_hash=AAEmAbiv5bkbsyHzxKjD2YFwwcZnkMPvFxrSL-UINEz8tg");
		}
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\exit.png").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\exit.png");
			urls.add("https://dl.dropboxusercontent.com/s/e0xbzeowe7q00xe/exit.png?dl=1&token_hash=AAECq1Hc4iI4XfRAeNjbF5GdkH3Q-AIZHiZ-AwpZyYK69Q");
		}
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\config.png").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\config.png");
			urls.add("https://dl.dropboxusercontent.com/s/3uwhvqf4pm5il16/config.png?dl=1&token_hash=AAF5MHfG4FVQvL93JoS6SMz5taaciXRmvcXJc0PVpvSiig");
		}
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\Background.png").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\Background.png");
			urls.add("https://dl.dropboxusercontent.com/s/w1f1ovaw4g1i0xl/Background.png?dl=1&token_hash=AAHM-Ll3pZwuxDLrS96Byg_FC48wZwh-endEGjpH8TdZgA");
		}
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\server_on.png").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\server_on.png");
			urls.add("https://dl.dropboxusercontent.com/s/sa1hydvuasdrs95/server_on.png?dl=1&token_hash=AAFqvi1hdo3gf7WZ_526uBJWu32KKRwbVp2dxHC08UM5dA");
		}
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\server_off.png").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\server_off.png");
			urls.add("https://dl.dropboxusercontent.com/s/c1wksd5q1wht4kt/server_off.png?dl=1&token_hash=AAF6qoDqUHfcTTEPIFc11RxxQYgEPn6LcyGklgY2WkU0dQ");
		}
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\back.png").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\back.png");
			urls.add("https://dl.dropboxusercontent.com/s/9q3x8uktcqs7uoq/back.png?dl=1&token_hash=AAG4cv1GgVzNV5W66Sqr5mj655LxxXVRVUbukSuQPI0Fcg");
		}
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\add.png").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\add.png");
			urls.add("https://dl.dropboxusercontent.com/s/ifw8l9cufwga999/add.png?dl=1&token_hash=AAHzK6b-eb04YyagfWWcJaKjAlR1LTqF7YtPIQLVg6UX1Q");
		}
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\enable.png").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\enable.png");
			urls.add("https://dl.dropboxusercontent.com/s/53xkukb1jydj9gp/enable.png?dl=1&token_hash=AAH5u9tGP-XTNGyQU_Ir3NOLY6YIAegkKaqFUZUsHQyZeg");
		}
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\disable.png").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\disable.png");
			urls.add("https://dl.dropboxusercontent.com/s/81gpv63sp5k7zcq/disable.png?dl=1&token_hash=AAGJlcPHTSGvx_techZJ_p8Qg3Af4TaeGOAgTwVF4rvo3g");
		}
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\delete.png").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\delete.png");
			urls.add("https://dl.dropboxusercontent.com/s/k7k73v44nbwf4xq/delete.png?dl=1&token_hash=AAFpz2kQauaospt5k40oIUgm30_GRTo_8VoDfmmD_VmgJQ");
		}
		
		// DownloadJob: Sounds
		
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\Sounds\\Notification.wav").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\Sounds\\Notification.wav");
			urls.add("https://dl.dropboxusercontent.com/s/x2hbz74q1jc64j4/Notification.wav?dl=1&token_hash=AAEE2PoOyGtaQx-jzUqS7ZS6-xXDcncRmq7ycqakIXTo3A");
		}
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\Sounds\\start.wav").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\Sounds\\start.wav");
			urls.add("https://dl.dropboxusercontent.com/s/xcnevytkiak9awn/start.wav?token_hash=AAEcL9kBaLl_MFVpwVUY2vHIRdrm7HdNqau7I6RIJ7vVHQ&dl=1");
		}
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\Sounds\\click.wav").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\Sounds\\click.wav");
			urls.add("https://dl.dropboxusercontent.com/s/m8o2uerq6jloz4a/click.wav?token_hash=AAFT76uXITNulBVN9fbP3WQFrslMhdVO2hbX1VZZie_M5Q&dl=1");
		}
		
		// DownloadJob: Natives
		
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\natives\\jinput-dx8_64.dll").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\natives\\jinput-dx8_64.dll");
			urls.add("https://dl.dropboxusercontent.com/s/557ayfeyz5tbzbo/jinput-dx8_64.dll?token_hash=AAHhgbSW3jP5wSfBiFiQjal3OsK4rsvOdyzp_eJNrMohCw&dl=1");
		}
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\natives\\jinput-dx8.dll").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\natives\\jinput-dx8.dll");
			urls.add("https://dl.dropboxusercontent.com/s/09q21gqbkpl5u8a/jinput-dx8.dll?token_hash=AAHn9HKzJL-e9vQv3lq6iF0sNH8pBUF_mnpdPGRWWaKHFw&dl=1");
		}
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\natives\\jinput-raw_64.dll").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\natives\\jinput-raw_64.dll");
			urls.add("https://dl.dropboxusercontent.com/s/1laz8jqfe0z134n/jinput-raw_64.dll?token_hash=AAFfxwm55gjFl5SU8UTEikZRXMbD3qNxOUMqSJrfCDaPVw&dl=1");
		}
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\natives\\jinput-raw.dll").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\natives\\jinput-raw.dll");
			urls.add("https://dl.dropboxusercontent.com/s/e3j4y5qkf603wy2/jinput-raw.dll?token_hash=AAFoneyz4WvXmIP6EesmEmlMXHQ7UpjFqXPTf_bIEtfv_A&dl=1");
		}
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\natives\\jinput-wintab.dll").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\natives\\jinput-wintab.dll");
			urls.add("https://dl.dropboxusercontent.com/s/5etpealciht9ogr/jinput-wintab.dll?token_hash=AAG8n_VBxZ-HCl0X6kPAohnUFcHlwDI7nH4C0QWv4-sW8w&dl=1");
		}
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\natives\\lwjgl64.dll").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\natives\\lwjgl64.dll");
			urls.add("https://dl.dropboxusercontent.com/s/91swrhya99fvvsz/lwjgl64.dll?token_hash=AAFEVaMU8RovXdAfvz4-st1043266LDfCmwYMYvw7bZe2Q&dl=1");
		}
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\natives\\lwjgl.dll").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\natives\\lwjgl.dll");
			urls.add("https://dl.dropboxusercontent.com/s/5je7yb0yr9zjqhf/lwjgl.dll?token_hash=AAETLanjEaPndgfV3FIt0XqCyI7BBqyI8KdXQk_gvt4gQw&dl=1");
		}
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\natives\\OpenAL32.dll").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\natives\\OpenAL32.dll");
			urls.add("https://dl.dropboxusercontent.com/s/dew5ee3utuspb3b/OpenAL32.dll?token_hash=AAGphbiMjHNHzpJWUqtTDSShSUd2tNQ9dqJiYvgODBLV4Q&dl=1");
		}
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\natives\\OpenAL64.dll").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\natives\\OpenAL64.dll");
			urls.add("https://dl.dropboxusercontent.com/s/40w3tntgr2ar79a/OpenAL64.dll?token_hash=AAGEZr0L0A_O2vhEIWBE01-kAi0PUe-bc1Sifv2emhhRrQ&dl=1");
		}
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\natives\\avutil-ttv-51.dll").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\natives\\avutil-ttv-51.dll");
			urls.add("https://dl.dropboxusercontent.com/s/957g68afsixod4v/avutil-ttv-51.dll?dl=1&token_hash=AAFwFM6OWfwA-otidrQvJoL1IIlOKYOthhYruAce3qYYFA");
		}
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\natives\\libmfxsw64.dll").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\natives\\libmfxsw64.dll");
			urls.add("https://dl.dropboxusercontent.com/s/3hfwzdk9d5zi9r3/libmfxsw64.dll?dl=1&token_hash=AAH_wWpUKvgN8O4YZj3f86_74lC5jahOGsLxXovOaurJUg");
		}
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\natives\\libmp3lame-ttv.dll").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\natives\\libmp3lame-ttv.dll");
			urls.add("https://dl.dropboxusercontent.com/s/ni3nswmoubymqt2/libmp3lame-ttv.dll?dl=1&token_hash=AAHgJj4kdFzzWL2YOoD2fXtcJMTUbEia-kBoDekkfG9PZg");
		}
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\natives\\swresample-ttv-0.dll").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\natives\\swresample-ttv-0.dll");
			urls.add("https://dl.dropboxusercontent.com/s/e8e2tyo9ag5gqm1/swresample-ttv-0.dll?dl=1&token_hash=AAGS2GAU_vE8iu3XmjO8pZGsiP7UYsKOEyqfDwhg8vcVOA");
		}
		if (!new File(System.getenv("APPDATA") + "\\.Slimecraft\\natives\\twitchsdk.dll").exists()) {
			files.add(System.getenv("APPDATA") + "\\.Slimecraft\\natives\\twitchsdk.dll");
			urls.add("https://dl.dropboxusercontent.com/s/5lukgt4yzpsnbq6/twitchsdk.dll?dl=1&token_hash=AAHNSdddk6KVoW2x1IpbHSPIkxXKkvb4tBO0h-7wp4zpZw");
		}
		
	}
	
	public static List<String> getFiles() {
		return files;
	}
	
	public static List<String> getURLs() {
		return urls;
	}

}
