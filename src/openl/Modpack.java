package openl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;



public class Modpack {

	private String id;
	private String name;
	private BufferedImage logo;
	private boolean isPrivate;
	private boolean unlocked;
	private String description = "No description available.";
	private String author = "Unknown";
	public String link = "";


	private int version = 0;
	private String versionString = "0.0.0";
	private String mcVersion = "1.7.2";
	private String serverID = "";

	private boolean downloaded = false;

	private Modpack clientVersion = null;
	private Modpack serverVersion = null;

	@Override
	public String toString() {
		return "{id=" + id + ";name=" + name + ";hasLogo=" + (logo != null) + ";private=" + isPrivate + (isPrivate ? (";unlocked=" + unlocked) : "") + ";author=" + author + ";version=" + version + ";versionString=" + versionString + ";mcversion=" + mcVersion + "}";
	}

	public Modpack(String id, String name, URL logo, String author, boolean isPrivate, int version, String versionString, String mcVersion, String serverID, String link) {
		this(id,name,logo,author,isPrivate,version,versionString,mcVersion,serverID);
		this.link = link;

	}

	public Modpack(String id, String name, URL logo, String author, boolean isPrivate, int version, String versionString, String mcVersion, String serverID) {
		this.id = id;
		this.name = name;
		try {
			this.logo = ImageIO.read(logo);
		} catch (Exception e) {}
		this.author = author;
		this.isPrivate = isPrivate;
		this.version = version;
		this.versionString = versionString;
		//TODO this.mcVersion = mcVersion;
		this.serverID = serverID;
		downloaded = new File(Utils.getInstancesFolder(), serverID + "_" + id + "/pack.json").exists();

		if(name == null){
			clientVersion = this;
		}else{
			serverVersion = this;
			clientVersion = Utils.getModpackFromLocalInfo(serverID, id);
			if(clientVersion != null){
				clientVersion.clientVersion = clientVersion;
				clientVersion.serverVersion = this;
			}
		}
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public BufferedImage getLogo() {
		return logo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String desc) {
		this.description = desc;
	}

	public void setAuthor(String creator) {
		this.author = creator;
	}

	public int getVersion() {
		return version;
	}

	public String getMcVersion() {
		return mcVersion;
	}

	public String getVersionString() {
		return versionString;
	}

	public String getAuthor() {
		return author;
	}

	public boolean hasDownloaded(){
		return downloaded;
	}

	public String getServerID() {
		return serverID;
	}

	public Modpack getClientVersion() {
		return clientVersion;
	}

	public Modpack getServerVersion() {
		return serverVersion;
	}

	public void setDownloaded(){
		downloaded = true;
	}

	public int getMemory(){
		return 1024;
	}


	public void play(){

	}

	public Object getMinecraftJar() {
		return new File(Utils.getMinecraftFolder(), "minecraft.jar");
	}

	public int getPermGen() {
		return 128;
	}

	public boolean hasExtraArguments() {
		return getExtraArguments()!=null && !getExtraArguments().equals("");
	}

	public String getExtraArguments() {
		return null;
	}

	public boolean hasMinecraftArguments() {
		return getMinecraftArguments()!=null && !getMinecraftArguments().equals("");
	}

	public String getMinecraftArguments() {
		return null;
	}

	public File getAssetsDir() {
		return new File(getRootDirectory(), "/assets");
	}

	public File getRootDirectory() {
		return Utils.getMinecraftFolder();
	}

	public String getMainClass() {
		return "net.minecraft.launchwrapper.Launch";
	}

	public File getJarModsDirectory() {
		return new File(getRootDirectory(), "/jarmods");

	}

	public boolean hasJarMods() {
		return false;
	}

	public String getJarOrder() {
		return null;
	}

	public String getBinDirectory() {
		return getRootDirectory().getAbsolutePath()+"/bin";
	}

	public String getLibrariesNeeded() {

		return "net.minecraftforge:minecraftforge:9.11.1.965,net.minecraft:launchwrapper:1.8," +
				"org.ow2.asm:asm-all:4.1,org.scala-lang:scala-library:2.10.2,org.scala-lang:scala-compiler:2.10.2,lzma:lzma:0.0.1";
	}

	public File getNativesDirectory() {
		return new File(getRootDirectory().getAbsolutePath(),"/natives");
	}


}
