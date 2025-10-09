package it.v1nc3nz0.musimathics.api.configuration;

import java.io.File;
import java.io.IOException;
import org.simpleyaml.configuration.file.YamlFile;

public class AbstractConfiguration extends YamlFile implements Configuration
{

	private File configFolder;
	private String configName;
	
	public AbstractConfiguration(File configFolder, String configName)
	{
		this.configFolder = configFolder;
		this.configName = configName;
	}
	
	@Override
	public void copyDefaultConfig()
	{
		FilesUtils.extractFromJar(getConfigFolder(), getConfigName());
	}

	@Override
	public File getConfigFolder()
	{
		return configFolder;
	}

	@Override
	public String getConfigName()
	{
		return configName;
	}

	public void loadConfiguration()
	{
		try
		{
			File file;
			
			if(getConfigFolder() == null) 
				file = new File(getConfigName());
			else
			{
				if(!getConfigFolder().exists())
					getConfigFolder().mkdirs();
				
				file = new File(getConfigFolder(),getConfigName());
			}
			
			if(!file.exists())
				copyDefaultConfig();
			
			setConfigurationFile(file);
			loadWithComments();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
