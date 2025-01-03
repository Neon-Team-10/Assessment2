package io.github.neonteam10;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;

public class TestFileHandleResolver implements FileHandleResolver{
    @Override
	public FileHandle resolve (String fileName) {
        System.out.println("src/main/resources/"+fileName);
        
		return Gdx.files.getFileHandle("src/main/resources/"+fileName,FileType.Internal);
	}
}

